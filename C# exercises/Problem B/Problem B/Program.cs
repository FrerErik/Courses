using System;
using System.Linq;

namespace Problem_B
{

    public class Comparer
    {
        public readonly string[] answers = new string[] { "gunilla has a point", "edward is right" };

        public string compareMinAndMax(int[] startVals, int[] endVals)
        {
            int maxInStart = startVals.Max();
            int minInEnd = endVals.Min();

            if (maxInStart > minInEnd)
            {
                return answers[1];
            } else
            {
                return answers[0];
            }
        }
        
    }

    internal class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine();
            int n = Int32.Parse(Console.ReadLine());
            int[] lookingAwayTimeStart = new int[n];
            int[] lookingAwayTimeEnd = new int[n];
            int index = 0;
            for (int i = 0; i < n; i++)
            {
                Console.WriteLine();
                string s = Console.ReadLine();
                
                string info = "";
                foreach(char c in s)
                {   
                  if (c ==  ' ')
                    {
                        lookingAwayTimeStart[index] = Int32.Parse(info);
                        info = "";
                    }
                  info += c;
                }
                lookingAwayTimeEnd[index] = Int32.Parse(info);
                index++;

                // done with UI
            }
            var comparer = new Comparer();
            Console.WriteLine(comparer.compareMinAndMax(lookingAwayTimeStart, lookingAwayTimeEnd));

        }
    }
}