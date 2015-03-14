%% Machine Learning Online Class - Exercise 2: Logistic Regression

%% Initialization
clear ; close all; clc

%% Load Data

data = load('ex2data2.txt');
X = data(:, [1, 2]); y = data(:, 3);
subplot(2,2,1)
plotData(X, y);
hold on;
xlabel('Microchip Test 1')
ylabel('Microchip Test 2')
legend('y = 1', 'y = 0')
hold off;

X = mapFeature(X(:,1), X(:,2));
initial_theta = zeros(size(X, 2), 1);
lambda = 1;

[cost, grad] = costFunctionReg(initial_theta, X, y, lambda);

fprintf('Cost at initial theta (zeros): %f\n', cost);

%% ============= Part 2: Regularization and Accuracies =============


function chiptest (lmbd, splt, colr, X, y )
initial_theta = zeros(size(X, 2), 1);
lambda = lmbd;
options = optimset('GradObj', 'on', 'MaxIter', 400);
[theta, J, exit_flag] = ...
	fminunc(@(t)(costFunctionReg(t, X, y, lambda)), initial_theta, options);
subplot(2,2,splt)
plotDecisionBoundary(theta, X, y, colr);
hold on;
title(sprintf('lambda = %g', lambda))
xlabel('Microchip Test 1')
ylabel('Microchip Test 2')
legend('y = 1', 'y = 0', 'Decision boundary')
hold off;

% Compute accuracy on our training set
p = predict(theta, X);
fprintf('Train Accuracy: %f\n', mean(double(p == y)) * 100);
endfunction

chiptest(0.0, 2, '-r',X,y);
chiptest(1.0, 3, '-b', X, y);
chiptest(100, 4, '-b', X, y);
