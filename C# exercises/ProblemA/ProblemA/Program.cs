using System;

namespace Sample
{
    public class Determiner
    {
        private readonly string[] outputs = new string[3] { "advertise", "does not matter", "do not advertise" };
        public string DetermineAdvertisingWorth(long[] profits)
        {
            long profitA = profits[0];
            long profitB = profits[1] - profits[2];
            if (profitA > profitB)
            {
                return outputs[2];
            }
            if (profitB > profitA)
            {
                return outputs[0];
            }
            if (profitA == profitB)
            {
                return outputs[1];
            } else
            {
                return "invalid";
            }
        }

    }
    class Test
    {
        public static void Main(string[] args)
        {
            int n;
            
            Console.WriteLine();
            n = Int16.Parse(Console.ReadLine());
            for (int i = 0; i < n;  i++)
            {
                long[] revenues = new long[3];
                Console.WriteLine();
                string values = Console.ReadLine();
                string saver = "";
                int start = -1;
                foreach (char c in values)
                {   
                    if (c == ' ') 
                    {
                        start = start + 1;
                        revenues[start] = Int64.Parse(saver);
                        saver = "";
                    }
                    saver += c;

                }
                revenues[start + 1] = Int64.Parse(saver);
                var det = new Determiner();
                Console.WriteLine(det.DetermineAdvertisingWorth(revenues));
            }
        }

    }

    
}