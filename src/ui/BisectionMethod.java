package ui;

import java.util.Scanner;

public class BisectionMethod {
    /**
     * Global instance of the Scanner class to avoid having to create a new instance
     */
    static Scanner input = new Scanner(System.in);
    /**
     * Method to print the value of an object
     * 
     * @param object a 
     */
    static void print(Object a) {
        System.out.println(a);
    }
    /**
     * Main method that runs the program and calls the other methods to run the
     * program
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        print("Welcome to the Bisection Method Calculator");
        print("Please select the function you would like to use");
        FunctionMenu();
        int desiredFunction = input.nextInt();
        double variableValue, constant, power, desirednum;
        // switch statement that assings the values of the variables to the
        // corresponding function chosen by the user
        switch (desiredFunction) {
            case 1:
                print("You have chosen function number 1: f(x) = 2cos(x^2)");
                variableValue = 100;
                constant = 0;
                power = 2;
                desirednum = 1;
                break;

            case 2:
                print("You have chosen function number 2: f(x) = (3x^3)+(7x^2)+5");
                variableValue = 0;
                constant = 5;
                power = 3;
                desirednum = 2;
                break;

            case 3:
                print("You have chosen function number 3: f(x) = xcos(x)");
                variableValue = 100;
                constant = 0;
                power = 1;
                desirednum = 3;
                break;

            default:
                print("Invalid input");
                return;
        }

        print("Enter the left endpoint of the interval: ");
        double a = input.nextDouble();
        print("Enter the right endpoint of the interval: ");
        double b = input.nextDouble();
        // method to find the value of the chosen function at the given interval
        double fa = function(desirednum, a, variableValue, constant, power);
        double fb = function(desirednum, b, variableValue, constant, power);
        if (fa * fb >= 0) {
            print("The bisection method is not valid within this interval");
            main(args);
        }
        int maxIterations = 100;
        double tolerance = 0.0000000001;
        for (int i = 0; i < maxIterations; i++) {
            double c = (a + b) / 2;
            double fc = function(desirednum, c, variableValue, constant, power);
            if (absolute(fc) < tolerance || (b - a) / 2 < tolerance) {
                print("The root of the function within the given interval is: " + c);
                print("Do you want to use the code again? (y/n)");
                String answer = input.next();
                if (answer.equals("y")) {
                    main(args);
                } else {
                    break;
                }
            }
            if (fa * fc < 0) {
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }
    }
    /**
     * implementation of the factorial function with a for loop instead of recursion
     * to avoid stack overflow
     * 
     * @param n number
     * @return The factorial of n
     */
    private static double factorial(int n) {
        double fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    /**
     * implementation of the power function to avoid using Math.pow()
     * 
     * @param x base
     * @param y exponent
     * @return The base x to the power of y
     */
    private static double pow(double x, double y) {
        double pow = 1;
        for (int i = 0; i < y; i++) {
            pow *= x;
        }
        return pow;
    }
    /**
     * implementation of the absolute value function to avoid using Math.abs()
     * 
     * @param x number
     * @return The absolute value of x
     */
    private static double absolute(double x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }
    /**
     * value of pi
     */
    private static double pi = 3.14159;
    /**
     * implementation of the cosine function to avoid using Math.cos()
     * 
     * @param x angle in radians
     * @param y number of iterations of the Taylor series
     * @return The cosine of x, with it's range reduced from [-pi,pi] with y iterations of the Taylor series 
     */
    private static double cos(double x, double y) {
        x=x % (2 * pi);
        if (x< -pi){
        x+=2*pi;
        } else if (x>pi){
        x-=2*pi;
        }
        double cos = 0;
        for (int i = 0; i <= y; i++) {
            cos += pow(-1, i) * pow(x, 2 * i) / factorial(2 * i);
        }
        return cos;
    } 

    /**
     * Menu of functions to choose from and their respective formulas
     */
    private static void FunctionMenu() {
        print("Function number 1: f(x) = 2cos(x^2)");
        print("Function number 2: f(x) = (3x^3)+(7x^2)+5");
        print("Function number 3: f(x) = x cos(x)");
    }
    /**
     * method that evaluates the chosen function at the given interval
     * 
     * @param desirednum the number of the chosen function
     * @param x the interval
     * @param variableValue the number of iterations of the Taylor series
     * @param constant the constant of the chosen function 
     * @param power the power of the chosen function
     * @return The value of the chosen function at the given interval
     */
    private static double function(double desirednum, double x, double variableValue, double constant, double power) {
        if (desirednum == 1) {
            return (2 * cos(pow(x, power), variableValue));
        } else if (desirednum == 2) {
            return (3 * pow(x, 3)) + (7 * pow(x, 2)) + constant;
        } else if (desirednum == 3) {
            return (x * cos(x, variableValue));
        }
        return 0.0;
    }
    // Example1: Choose the first function, enter the interval [1,2], the program will output "the root of the function within the given interval is: 1.2533141372841783"
    // Example2: Choose the second function, enter the interval [1,2], the program will output "no root in this interval"
    // Example3: Choose the third function, enter the interval [1,2], the program will output "the root of the function within the given interval is: 1.5707963267341256"
}