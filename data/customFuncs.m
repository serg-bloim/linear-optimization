(* ::Package:: *)

BeginPackage["customFuncs`"]
Unprotect @@ Names["customFuncs`*"];
ClearAll @@ Names["customFuncs`*"];



GetLine::usage = "GetLine function";
P2L::usage = "GetLine function";
GetRestrictionMatrix::usage = "GetLine function";
GetRestrictions::usage = "GetRestricts function";
GetPiPSCode::usage = "GetPiPSCode function";
LinesIntersection::usage = "LinesIntersection function";
LC::usage = "LC function";
GetCol::usage = "GetCol function";
ABDecomp::usage = "ABDecomp function";
ABComp;
RowReduceAdvanced::usage = "RowReduceAdvanced function";
IdendityComplete::usage = "IdendityComplete function";
IsSatisfy::usage = "IsSatisfy function";
GetLineByPoints;
OrientLineToPoint;
VComp::usage = "VComp function";
MaxAbs::usage = "maximum  absolute value in a list";
Subscript[X_, pos__]:=X[[pos]];
ExportTest;
ToFixedWidth;



Begin["`Private`"];


(* ::Title:: *)
(*Geometry*)


(* ::InlineFormula:: *)
(*Lines Intersection Point 2D*)


LinesIntersection[{a1_,b1_,c1_},{a2_,b2_,c2_}]:={(-b2 c1+b1 c2)/(-a2 b1+a1 b2),(-a2 c1+a1 c2)/(a2 b1-a1 b2)}


(* ::InlineFormula:: *)
(*Lines Intersection Number*)


LC[n_]:=n*(n-1)/2;


(* ::InlineFormula:: *)
(*Lines by 2 points*)


GetLineByPoints[pt1_,pt2_:0]:=Module[{p1,p2,n,m,x1,x2,y1,y2,res},
If[{2,2}==Dimensions[pt1],p2=pt1[[2]];p1=pt1[[1]],p1=pt1;p2=pt2];
{x1,y1}=p1;
{x2,y2}=p2;
res={y1-y2,x2-x1,x1*y2-x2*y1};
Return[res];
];


(* ::InlineFormula:: *)
(*Orient line to point*)


OrientLineToPoint[line_,p_:{0,0}]:=Module[{res},
(*If[2<=Length[Dimensions[line]],Return[OrientLineToPoint[#,p]/@line];,*)
If[line.Append[p,1]<=0,res=line,res=-line];
Return[res];
(*]*)
];


(* ::Title:: *)
(*Restrictions funcs below*)


GetLine[{m_,n_}]:=Module[{mx,my,nx,ny,c},{mx,my}=m;{nx,ny}=m-n;c=-m.(m-n);Return[{nx,ny,c}];]
P2L[ps_]:=Module[{l},
l=Partition[ps,2];
If[OddQ[Length[ps]],l=Append[l,{ps[[Length[ps]]],{0,0}}]];
Return[l];
]
GetRestricts[ps_, vars_]:=#<=0&/@(GetRestrictionMatrix[ps].Append[vars,1]);
(*Types a PiPS constraint code for matrix M from GetRestrictionMatrix function*)
GetPiPSCode[A_,B_,C_,R_:"<="]:=Module[{x,n,m, res,b,a,objs,r},
{m,n}=Dimensions[A];
b=B;r=ToString[R];
x=Table["*X"<>ToString[i],{i,1,n}];
objs="max:\t\t"<>ToString[(ToString[#]&/@C).x]<>"\n";
a=Map[ToString[#]&,A,{2}];
x = a.x;
x="\nConstraint:\t"<>ToString[#]<>r&/@x;
x=Table[x[[i]]<>ToString[b[[i]]]<>";",{i,m}];
x=StringJoin[x];
Return[objs<>x];
];


(* ::InlineFormula:: *)
(*Get restrictions*)


GetRestrictionMatrix[points_,center_:{0,0}]:=Module[{res},
res=Partition[points,2];
res=GetLineByPoints[#]&/@res;
res=OrientLineToPoint[#,center]&/@res;
Return[res];
];


GetRestrictions[points_,varlist_:{Global`x1,Global`x2}]:=Module[{res,vl=Append[varlist,1]},
res=Partition[points,2];
res=GetLineByPoints[#]&/@res;
res=OrientLineToPoint[#,{3,7}]&/@res;
res=res.vl;
res=#<=0&/@res;
Return[res];
];


(* ::Title:: *)
(*Matrix*)


GetCol[m_,cn_]:=Transpose[m][[cn]];
ABDecomp[m_]:=Module[{mt=Transpose[m]},
Return[{Transpose[Take[mt,Length[mt]-1]],
mt[[Length[mt]]]}];
];
ABComp[A_,B_]:=Module[{},
Return[Transpose[Transpose[A]~Join~{B}]];
];
RowReduceAdvanced[m_,ml_]:=Module[{},
Join[\!\(\*
TagBox[
FrameBox[
SubscriptBox["list", "1"]],
"SelectionPlaceholder"]\),\!\(\*
TagBox[
FrameBox[
SubscriptBox["list", "2"]],
"Placeholder"]\)]


1];
IdendityComplete[M_]:=Transpose[Transpose[M]~Join~IdentityMatrix[Length[M]]];


IsSatisfy[m_,v_]:=Module[{s},s=(m.Append[v,1])];


(* ::Title:: *)
(*Vector*)


VComp[v1_,v2_,eps_:0]:=Module[{d,p,n},
d=v2-v1;
p=Count[d,x_/;x>eps];
n=Count[d,x_/;x<eps];
If[p==n==0,Return[0]];
If[p==0,Return[-1]];
If[n==0,Return[1]];
Return["different"];
];
MaxAbs[list_]:=Max[Abs[list]];


vectorMaxDistance[v1_,v2_]:=Max[Abs[v1-v2]];


(* ::Title:: *)
(*String*)


ToFixedWidth[n_, width_] := 
  StringJoin[PadLeft[Characters[ToString[n]], width, "0"]];


(* ::Title:: *)
(*Import/Export*)


ExportTest[Data_, pref_String:"data", dataDir_:"test"]:=Module[{n=0,dir,sn},
dir=Directory[];
SetDirectory[dataDir];
n=Import["control.dat"][[1,1]];
sn=StringJoin[pref,"_",ToFixedWidth[n,8],".dat"];
(*Put[Data,sn];*)
Export[StringJoin[pref,"_",ToFixedWidth[n,8],".dat"],Data];
(n+1)>>"control.dat";
SetDirectory[dir];
];


End[]
Protect @@ Names["customFuncs`*"];
(*Privare @@ Names["*)
EndPackage[]
