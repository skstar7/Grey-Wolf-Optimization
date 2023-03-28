import java.util.*;
class SphereFunction extends funBench {
    public double func(double[] x) {
        double result = 0;
        for (int i = 0; i < x.length; i++) {
            result += x[i] * x[i];
        }
        return result;
    }
}
class RastriginFunction extends funBench {
    double func(double[] x) {
        double sum = 0;
        double A = 10;
        int n = x.length;
        for (int i = 0; i < n; i++) {
            sum += (x[i] * x[i]) - A * Math.cos(2 * Math.PI * x[i]);
        }
        return A * n + sum;
    }
}

 class AckleyFunction extends funBench {

    double func(double x[]) {
        double sum1 = 0;
        double sum2 = 0;
        for (int i = 0; i < x.length; i++) {
            sum1 += x[i] * x[i];
            sum2 += Math.cos(2 * Math.PI * x[i]);
        }
        return -20 * Math.exp(-0.2 * Math.sqrt(sum1 / x.length)) - Math.exp(sum2 / x.length) + 20 + Math.E;
    }
}
 class RosenbrockFunction extends funBench {

    double func(double x[]) {
        double sum = 0;
        for (int i = 0; i < x.length - 1; i++) {
            double a = x[i + 1] - x[i] * x[i];
            double b = 1 - x[i];
            sum += 100 * a * a + b * b;
        }
        return sum;
    }
}

class DixonPriceFunction extends funBench {

    double func(double[] x) {
        double sum = Math.pow(x[0] - 1, 2);

        for (int i = 1; i < x.length; i++) {
            sum += (i + 1) * Math.pow(2 * Math.pow(x[i], 2) - x[i - 1], 2);
        }

        return sum;
    }
}







public class Main {

    //mean function
    public static double mean(double arr[]){
        double total = 0;
        for (int i = 0 ;i<arr.length;i++){
            total +=arr[i];
        }

       return total/arr.length;
    }


    // Function for calculating median
    public static double findMedian(double arr[]) {
        // First we sort the array
        Arrays.sort(arr);
        int n = arr.length;
        // check for even case
        if (n % 2 != 0)
            return arr[n / 2];

        return (arr[(n - 1) / 2] + arr[n / 2]) / 2.0;
    }

    //method to calculate standard deviation
    public static double standardDeviation(double arr[]) {

        // calculate the standard deviation
        double standardDeviation = 0.0;
        for (double num : arr) {
            standardDeviation += Math.pow(num - mean(arr), 2);
        }

        return Math.sqrt(standardDeviation /arr.length);
    }
    public static double minCalculate(double arr[]){
        double min = arr[0];
        //Loop through the array
        for (int i = 0; i < arr.length; i++) {
            //Compare elements of array with min
            if(arr[i] <min)
                min = arr[i];
        }
        return min;

    }
    public static double maxCalculate(double arr[]){
        double max  = arr[0];
        for (int i = 0; i < arr.length; i++) {
            //Compare elements of array with min
            if(arr[i] >max)
                max= arr[i];
        }
        return  max;
    }




    public static void main(String[] args) {
        Scanner sk = new Scanner(System.in);

        System.out.println("Press 1 to test SphereFunction\nPress 2 to test RastriginFunction\nPress 3 to test AckleyFunction\nPress 4 to test Rosenbrock Function\npress 5 Dixon Price Function");
        int option = sk.nextInt();


        switch (option) {
            case 1: {
                double[] Lower = {-5.12, -5.12};
                double[] Upper = {5.12, 5.12};
                int maxiter = 100;
                int N = 50;
                SphereFunction fb2 = new SphereFunction();
                double[] soln = new double[50];
                grey_Wolf_Optimization obj1 = new grey_Wolf_Optimization(fb2, Lower, Upper, maxiter, N);
                for (int i = 0; i < 50; i++) {

                    System.out.print("for " + i + " no. run ");
                    soln[i] = obj1.getOptimizeSoliution();
                    System.out.println(soln[i]);
                }
                System.out.println("Mean of the sphear function is " + mean(soln));
                System.out.println("Median of the sphear function is " + findMedian(soln));
                System.out.println("Standard deviation of the sphear function is "+standardDeviation(soln));
                System.out.println("Min of the sphere functio n" + minCalculate(soln));
                System.out.println("Max of the sphere functio n" + maxCalculate(soln));
                break;
            }
            case 2:{
                double[] Lower = {-5.12, -5.12};
                double[] Upper = {5.12, 5.12};
                int maxiter = 100;
                int N = 50;
                RastriginFunction fb3 = new RastriginFunction();
                double[] soln = new double[50];
                grey_Wolf_Optimization obj2 = new grey_Wolf_Optimization(fb3, Lower, Upper, maxiter, N);
                for (int i = 0; i < 50; i++) {

                    System.out.print("for " + i + " no. run ");
                    soln[i] = obj2.getOptimizeSoliution();
                    System.out.println(soln[i]);
                }
                System.out.println("Mean of the Rastrigin Function is " + mean(soln));
                System.out.println("Median of the RastriginFunction " + findMedian(soln));
                System.out.println("Standard deviation of the RastriginFunction is "+standardDeviation(soln));
                System.out.println("Min of the Rastrigin functio n" + minCalculate(soln));
                System.out.println("Max of the Rastrigin functio n" + maxCalculate(soln));
                break;
            }
            case 3:{
                double[] Lower = {-32, -32};
                double[] Upper = {32, 32};
                int maxiter = 100;
                int N = 50;
                AckleyFunction fb3 = new AckleyFunction();
                double[] soln = new double[50];
                grey_Wolf_Optimization obj3 = new grey_Wolf_Optimization(fb3, Lower, Upper, maxiter, N);
                for (int i = 0; i < 50; i++) {

                    System.out.print("for " + i + " no. run ");
                    soln[i] = obj3.getOptimizeSoliution();
                    System.out.println(soln[i]);
                }
                System.out.println("Mean of the Ackley Function is " + mean(soln));
                System.out.println("Median of the Ackley Function " + findMedian(soln));
                System.out.println("Standard deviation of the Ackley Function is "+standardDeviation(soln));
                System.out.println("Min of the Ackley functio n" + minCalculate(soln));
                System.out.println("Max of the Ackley functio n" + maxCalculate(soln));
                break;

            }
            case 4: {
                double[] Lower = {-5, -5};
                double[] Upper = {10, 10};
                int maxiter = 100;
                int N = 50;
                RosenbrockFunction fb3 = new RosenbrockFunction();
                double[] soln = new double[50];
                grey_Wolf_Optimization obj3 = new grey_Wolf_Optimization(fb3, Lower, Upper, maxiter, N);
                for (int i = 0; i < 50; i++) {

                    System.out.print("for " + i + " no. run ");
                    soln[i] = obj3.getOptimizeSoliution();
                    System.out.println(soln[i]);
                }
                System.out.println("Mean of the RosenbrockFunction Function is " + mean(soln));
                System.out.println("Median of the RosenbrockFunction " + findMedian(soln));
                System.out.println("Standard deviation of the RosenbrockFunction " + standardDeviation(soln));
                System.out.println("Min of the  Rosenbrock functio n" + minCalculate(soln));
                System.out.println("Max of the Rosenbrock functio n" + maxCalculate(soln));
                break;
            }
            case 5:{
                double[] Lower = {-10, -10};
                double[] Upper = {10, 10};
                int maxiter = 100;
                int N = 50;
                DixonPriceFunction fb3= new DixonPriceFunction();
                double[] soln = new double[50];
                grey_Wolf_Optimization obj3 = new grey_Wolf_Optimization(fb3, Lower, Upper, maxiter, N);
                for (int i = 0; i < 50; i++) {

                    System.out.print("for " + i + " no. run ");
                    soln[i] = obj3.getOptimizeSoliution();
                    System.out.println(soln[i]);
                }
                System.out.println("Mean of the Dixon Price Function is " + mean(soln));
                System.out.println("Median of the Dixon PriceFunction " + findMedian(soln));
                System.out.println("Standard deviation of the Dixon Price Function " + standardDeviation(soln));
                System.out.println("Min of the Dixon Price functio n" + minCalculate(soln));
                System.out.println("Max of the Dixon Price functio n" + maxCalculate(soln));
                break;

            }
            default:{
                System.out.println("Enter a valid no.");
            }
        }
    }
}