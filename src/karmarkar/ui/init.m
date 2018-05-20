SetDirectory["data"];
Clear["Global`*"];
<< karmarkar`
<< karmarkarAdvanced`
<< customFuncs`
cf = {};
a = {};
eps = 0.0001;
itermax = 400;
hardIterMax = 400;
BreakOnItermax[iter_] := NumberQ[itermax] && iter >= itermax;
BreakOnVectorDiff[xPrev_, xCur_] := MaxAbs[xCur - xPrev] < Abs[eps];
BreakOnExactOptimum[x_] := False;
KAlgorithmNew[] :=
  Module[{xk, x0, n, m, r, alp, isoptimum = 0,
    cfunc}, (*itermax_integer will be 50 by default if it is \
omitted*){m, n} = Dimensions[a];  (*'[a]' must be a full array*)
   xk = Table[1/n, {n}]; (*'Table' generates a list of n copies of '1/
   n' so we have a vector (1/n,...1/n) with n elements*)
   r = 1/Sqrt[n*(n - 1)];
   alp = 2/9;
   iter = 0;
   iters = {};
   While[iter++ < hardIterMax,
    x0 = xk;
    xk = KIter[xk, a, cf, alp, r] // N;
    AppendTo[iters, xk];
    If[BreakOnItermax[iter], Return["maxiter"]];
    If[BreakOnVectorDiff[x0, xk], Return["vector_diff"]];
    If[BreakOnExactOptimum[ xk], Return["exact_optimum"]];
    ];
   Return["absolute_maxiter"];
   ];
TestFunc[]:=42;