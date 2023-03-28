import java.util.*;
abstract class funBench {
    abstract double func(double x[]);
}

public class grey_Wolf_Optimization {
    double r1;
    double r2;
    int N;
    int D;
    int maxiter;
    double alfa[];
    double beta[];
    double delta[];
    double Lower[];
    double Upper[];
    funBench fb;
    double XX[][];
    double X1[][];
    double X2[][];
    double X3[][];
    double BESTVAL;
    double a[];
    double A1[];
    double C1[];
    double A2[];
    double C2[];
    double A3[];
    double C3[];

    public grey_Wolf_Optimization (funBench ifb,double iLower[],double iUpper[],int imaxiter,int iN) {// constractor
        maxiter=imaxiter;
        fb=ifb;
        Lower=iLower;
        Upper=iUpper;
        N=iN;
        D=2;

        a=new double[D];
        XX=new double[N][D];
        alfa=new double[D];
        beta=new double[D];
        delta=new double[D];
        A1=new double[D];
        C1=new double[D];
        A2=new double[D];
        C2=new double[D];
        A3=new double[D];
        C3=new double[D];
        X1=new double[N][D];
        X2=new double[N][D];
        X3=new double[N][D];

    }
    double[][] specificBounds(double s[][]) {
        for(int i=0;i<N;i++) {
            for(int j=0;j<D;j++) {
                if(s[i][j]<Lower[j]) {
                    s[i][j]=Lower[j]*((Upper[j]-Lower[j])*Math.random());
                }
                if(s[i][j]>Upper[j]){
                    s[i][j]=Lower[j]*((Upper[j]-Lower[j])*Math.random());
                }
//                System.out.println(s);
            }
        }
        return s;
    }


    double[][] sort_and_index(double[][] XXX) {
        double[] fitnesValue=new double[N];
        for(int i=0;i<N;i++) {
            fitnesValue[i]=fb.func(XXX[i]);
        }


        ArrayList<Double> newFitnessValue=new ArrayList<Double>(); //Copy the fitnessvaule on newFitnessValue
        for(int i=0;i<N;i++) {
            newFitnessValue.add(fitnesValue[i]);
        }

        ArrayList<Double> storeNewFitVal = new ArrayList<Double>(newFitnessValue);  // store newFitnessValue for latter use

        Collections.sort(newFitnessValue);

        double[] sortedValue=new double[newFitnessValue.size()];

        Iterator<Double> iterator=newFitnessValue.iterator();   //
        int ii=0;
        while(iterator.hasNext()) {
            sortedValue[ii]=iterator.next().doubleValue();
            ii++;
        }


        int[] indexes=new int[newFitnessValue.size()];
        for(int n=0;n<newFitnessValue.size(); n++) {
            indexes[n]=storeNewFitVal.indexOf(newFitnessValue.get(n));
        }
        double[][] B=new double[N][D];
        for(int i=0;i<N;i++) {
            for(int j=0;j<D;j++) {
                B[i][j]=XXX[indexes[i]][j];
            }
        }

        return B ;
    }


    void init(){
        for(int i=0;i<N;i++) {
            for(int j=0;j<D;j++) {
                XX[i][j]=Lower[j]+(Upper[j]-Lower[j])*Math.random();
            }
        }
        XX=sort_and_index(XX);
        for(int i=0;i<D;i++) {
            alfa[i]=XX[0][i];
        }
        for(int i=0;i<D;i++) {
            beta[i]=XX[1][i];
        }
        for(int i=0;i<D;i++) {
            delta[i]=XX[2][i];
        }

    }




    double[][] solution() {
        init();

        int iter=1;
        while(iter<=maxiter) {

            for(int j=0;j<D;j++) {
                a[j]=2.0-((double)iter*(2.0/(double)maxiter));
            }

            for(int i=0;i<N;i++)
            {
                for(int j=0;j<D;j++)
                {
                    r1=Math.random();
                    r2=Math.random();
                    for(int ii=0;ii<D;ii++) {
                        A1[ii]=2.0*a[ii]*r1-a[ii];
                    }
                    for(int ii=0;ii<D;ii++) {
                        C1[ii]=2.0*r2;
                    }

                    X1[i][j]=alfa[j]-A1[j]*(Math.abs(C1[j]*alfa[j]-XX[i][j]));
                    X1= specificBounds(X1);

                    r1=Math.random();
                    r2=Math.random();
                    for(int ii=0;ii<D;ii++) {
                        A2[ii]=2.0*a[ii]*r1-a[ii];
                    }
                    for(int ii=0;ii<D;ii++) {
                        C2[ii]=2.0*r2;
                    }

                    X2[i][j]=beta[j]-A2[j]*(Math.abs(C2[j]*beta[j]-XX[i][j]));
                    X2= specificBounds(X2);

                    r1=Math.random();
                    r2=Math.random();
                    for(int ii=0;ii<D;ii++) {
                        A3[ii]=2.0*a[ii]*r1-a[ii];
                    }
                    for(int ii=0;ii<D;ii++) {
                        C3[ii]=2.0*r2;
                    }

                    X3[i][j]=delta[j]-A3[j]*(Math.abs(C3[j]*delta[j]-XX[i][j]));
                    X3= specificBounds(X3);


                    XX[i][j]=(X1[i][j]+X2[i][j]+X3[i][j])/3.0;

                }
            }
            XX= specificBounds(XX);
            XX=sort_and_index(XX);

            for(int i=0;i<D;i++) {
                XX[N-1][i]=XX[0][i];
            }

            for(int i=0;i<D;i++) {
                alfa[i]=XX[0][i];
            }
            for(int i=0;i<D;i++) {
                beta[i]=XX[1][i];
            }
            for(int i=0;i<D;i++) {
                delta[i]=XX[2][i];
            }


                double bestFitness = fb.func(alfa);
//                BESTVAL = new double[][] { { bestFitness }, alfa };
//            System.out.println(iter+" no . "+bestFitness);
            System.out.println(bestFitness);
            iter++;
        }




        double[][] bestSoliutionFound=new double[2][D];
        for(int i=0;i<D;i++) {
            bestSoliutionFound[1][i]=alfa[i];
        }
        bestSoliutionFound[0][0]=fb.func(alfa);
        return bestSoliutionFound;

    }








    public double getOptimizeSoliution() {
        double[][] in=solution();
        return in[0][0];
//        System.out.println("Optimized value = "+in[0][0]);
//        for(int i=0;i<D;i++) {
//            System.out.println("x["+i+"] = "+in[1][i]);
//        }
    }

}


