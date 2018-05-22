(* ::Package:: *)

BeginPackage["karmarkarAdvanced`"];
Unprotect @@ Names["karmarkarAdvanced`*"];
ClearAll @@ Names["karmarkarAdvanced`*"];
getExactSolution::usage = "Karmarkar iteration";
composeRestrictions::usage = "create restrictions";
createDistances;
IsSatisfiedKarmarkar;
IsEqual;
IsZero;
IsOptimalKarmarkar;
OptimalCriteria;


Begin["`Private`"];
Distance[t_List,p_List]:=Module[{tn=Append[t,1],dist},
dist=Abs[tn.p]/Sqrt[Sum[p[[i]]^2,{i,1,Length[p]-1}]];
Return[dist];
];
createDistances[p_,rs_]:=Module[{res},
res=Table[Distance[p,rs[[i]]],{i,1,Length[rs]}];
Return[res];
];
getExactSolution[p_,rss_]:=Module[{n,m,rs,sorted,order,first,res,ab,b,dists,am,bv},
{m,n}=Dimensions[rss];
n=n-1;
rs=rss;
dists=createDistances[p,rs];
order=Ordering[dists,All,Less];
rs=rs[[order]];
am=Take[rs,n,n];
bv=-Take[Transpose[rs][[n+1]],n];
res=LinearSolve[am,bv];
Return[If[Length[res]==n,res,Table[10,{n}]]];
];
createBasis[n_]:=Module[{M},
M=IdentityMatrix[n];
M=Append[M,Table[0,{n}]];
M=Transpose[M];
Return[M];
];
composeRestrictions[A_]:=Module[{B,n,m,An,X,res},
{m,n}=Dimensions[A];
B=createBasis[n];
An=Transpose[Append[Transpose[A],Table[0,{m}]]];
X=Table[1,{n}];
X={Append[X,-1]};
res=Join[B,An,X];
Return[res];
];
IsEqual[val1_,val2_,eps_:10^-10]:=If[Abs[val1-val2]<eps,True,False];
IsZero[val_,zeroEps_:10^-10]:=IsEqual[val,0,zeroEps];
IsSatisfiedKarmarkar[x_,A_,zeroEps_:10^-10]:=Module[{t,ec,pc},
If[Length[x]!=Length[A[[1]]],Return[False]];
t=A.x;
t=IsZero/@t;
ec=IsEqual[Total[x],1];
pc=Min[(x/.v_/;v<0&&IsZero[v]->0)]>=0;
t=Join[t,{ec,pc}];
Return[FreeQ[t,False]];
];
MaxAbs[list_]:=Max[Abs[list]];
IsOptimalKarmarkar[x_,A_,z_,zeroEps_:10^-10]:=TrueQ[IsSatisfiedKarmarkar[x,A,zeroEps]]&&TrueQ[IsZero[x.z]];
OptimalCriteria[x_,xold_,xe_,A_,z_,objDif_:10^-5,xDif_:10^-5,zeroEps_:10^-10]:={TrueQ[MaxAbs[x.z]<=objDif],TrueQ[MaxAbs[(x-xold).z]<=xDif],IsOptimalKarmarkar[xe,A,z,zeroEps]};


End[]
Protect @@ Names["karmarkarAdvanced`*"];
(*Privare @@ Names["*)
EndPackage[]









