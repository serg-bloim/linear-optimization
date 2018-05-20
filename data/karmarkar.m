(* ::Package:: *)

BeginPackage["karmarkar`"];
Unprotect @@ Names["karmarkar`*"];
ClearAll @@ Names["karmarkar`*"];
KIter::usage = "Karmarkar iteration"; (*Define a usage "Karmarkar iteration" message for a function 'KIter'*)
KAlgorithm::usage = "Karmarkar algorithm";
TestKarmarkarParams::usage = "Karmarkar algorithm";


Begin["`Private`"];
AddRow[m_,r_]:=Join[m,{r}];
KAlgorithm[cf_,a_, eps_Real, itermax_Integer:50]:=Module[{xk,x0,n,m,r,alp,iter,isoptimum=0,cfunc}, (*itermax_integer will be 50 by default if it is omitted*)
{m,n}=Dimensions[a];  (*'[a]' must be a full array*)
xk=Table[1/n,{n}]; (*'Table' generates a list of n copies of '1/n' so we have a vector (1/n,...1/n) with n elements*)
r=1/Sqrt[n*(n-1)];alp=2/9;
iter=0;
While[iter++<itermax,
x0=xk;
xk=KIter[xk,a,cf,alp,r]//N;
If[MaxAbs[xk-x0]<Abs[eps],isoptimum=1;Break[]];
];
cfunc=cf.xk;
Return[{xk, iter-1, cfunc}];
];
MaxAbs[list_List]:=Max[Abs[Max[list]],Abs[Min[list]]];
KIter[x_,A_,C0_,a_,r_]:=Module[{D,P,Pt,PPti,Il,I,n,c,cabs,yn,xn},
n=Dimensions[A][[2]];
D=DiagonalMatrix[x];
Il=Table[1,{n}];
P=AddRow[A.D,Il];
I=IdentityMatrix[n]; (*eto edinichnaya matrica*)
Pt=Transpose[P];
PPti=Inverse[P.Pt]; (*obratnaya matrica*)
c=(I-Pt.PPti.P).Transpose[{C0}.D];
cabs=Norm[c];
yn=Transpose[{Il/3}]-a*r*c/cabs;
xn=D.yn/(Il.D.yn)[[1]];
{xn}=Transpose[xn];
Return[xn];
];
TestKarmarkarParams[c_, a_, x_:0]:=Module[{nx=x,ct,at,xt, ts,errs},
ts={};
If[x==0,nx=c];
ts=Append[ts,VectorQ[c]];
ts=Append[ts,MatrixQ[a]];
ts=Append[ts,VectorQ[nx]];
ts=Append[ts,Length[c]==Length[nx]==Dimensions[a][[2]]];
errs = Count[ts,False]; (*gives the number of False elements in list 'ts'*)
Return[errs];
];
End[]
Protect @@ Names["karmarkar`*"];
(*Privare @@ Names["*)
EndPackage[]
