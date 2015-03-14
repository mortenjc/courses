function [C, sigma] = findBestCsigma(X, y, Xval, yval)

vals = [0.01 0.03 0.1 0.3 1 3 10 30];
C = 0;
sigma = 0;
errormin = 10^20;


% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

for i=1:length(vals)
 for j=1:length(vals)
   Ct = vals(i);
   sigmat = vals(j);

   model = svmTrain(X,y,Ct, @(x1, x2) gaussianKernel(x1, x2, sigmat));

   pred = svmPredict(model, Xval);
   error = mean(double(pred ~= yval));
   if (error < errormin)
     C = Ct;
     sigma = sigmat;
     errormin = error;
     printf("best so far: error %f, C: %f, sigma: %f", errormin, C, sigma);
   endif
  endfor
endfor

C
sigma

% =========================================================================

end
