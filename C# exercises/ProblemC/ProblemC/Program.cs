using System;
using System.Collections;
using System.Linq;

namespace ProblemC
{
    public class Functions
    {
        public int[] ToIntArrSort(ArrayList list, int iterations)
        {
            int[] weights = new int[iterations];
            for (int i = 0; i < list.Count; i++)
            {
                weights[i] = (int)list[i];
            }
            return weights;
        }

        public bool SubsetSumLocated(int[] weights, int iterations, int total) 
        {
            bool[, ] subset = new bool[total + 1, iterations + 1];

            for (int i = 0; i <= iterations; i++)
            {
                subset[0, i] = true;
            }

            for (int i = 1; i <= total; i++)
            {
                subset[i, 0] = false;
            }

            for (int i = 1; i <= total; i++)
            {
                for (int o = 1; o <= iterations; o++)
                {
                    subset[i, o] = subset[i, o - 1];
                    if (i >= weights[o - 1])
                    {
                        subset[i, o] = subset[i, o] || subset[i - weights[o - 1], o - 1 ];
                    }
                }
            }
            return subset[total, iterations];

        }

        public string splitWeights(int[] weights, int iterations)
        {
            int Kattis = 0;int cat = 0;int total = 0;
            total = weights.Sum();
            for (int i = 0; i<= total/2; i++)
            {

                if (SubsetSumLocated(weights, iterations, total/2 - i)) 
                {
                    cat = total/2 - i;
                    Kattis = total - (total/2 - i);
                    break;
                }



            }
            return Kattis.ToString() + " " + cat.ToString(); 
        }

        public string getEvenWeights(ArrayList list, int iterations)
        {
            int[] weights = ToIntArrSort(list, iterations);
            string answer = splitWeights(weights, iterations);
            return answer;
        }

    }
    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine();
            string line = Console.ReadLine();

            while (line != "0")
            {
                ArrayList weights = new ArrayList();
                String value = "";
                bool firstIter = true;
                int iterations = 0;
                foreach(char c in line)
                {
                    if (firstIter)
                    {
                        if (c == ' ')
                        {
                            iterations = Int32.Parse(value);
                            firstIter = false;
                            value = "";
                        }
                    } else
                    {
                        if (c == ' ')
                        {
                            weights.Add(Int32.Parse(value));

                            value = "";
                        }
                    }
                    value += c;


                }
                weights.Add(Int32.Parse(value));
                Functions functions = new Functions();
                Console.WriteLine(functions.getEvenWeights(weights, iterations));
                line = Console.ReadLine();
            }
        }
    }
}