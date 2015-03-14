Xadded = [ones(m,1) X];
a2 = sigmoid(Theta1*Xadded');
a2added = [ones(1,m) ; a2];
a3 = sigmoid(Theta2*a2added);

y2 = zeros(m,num_labels);
for i = 1:m
   y2(i,y(i)) = 1;
endfor

