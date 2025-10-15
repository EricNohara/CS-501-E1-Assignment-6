using System;
using System.Collections.Generic;
using System.Data.SqlTypes;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace Color_Transformer
{
    internal class Program
    {
        //the delegate for the transform operations
        delegate Tuple<int, int, int> ColorTransform(int r, int g, int b);
        
        //the three transform methods
        private static Tuple<int, int, int> brighten(int r, int g, int b)
        {
            Tuple<int, int, int> ret = Tuple.Create(Math.Min(r+30, 255), Math.Min(g + 30, 255), Math.Min(b + 30, 255));
            return ret;
        }
        private static Tuple<int, int, int> darken(int r, int g, int b)
        {
            Tuple<int, int, int> ret = Tuple.Create(Math.Max(r - 30, 0), Math.Max(g - 30, 0), Math.Max(b - 30, 0));
            return ret;
        }
        private static Tuple<int, int, int> invert(int r, int g, int b)
        {
            Tuple<int, int, int> ret = Tuple.Create(255 - r, 255 - g, 255 - b);
            return ret;
        }

        //method to take in an rgb value and a transform and apply it
        static private Tuple<int, int, int> ApplyTransform(int r, int g, int b, ColorTransform transform)
        {
            return transform(r, g, b);
        }

        static void Main(string[] args)
        {
            Random random = new Random();
            ColorTransform transform;
            Console.WriteLine("Color transformer test:");
            //generate random color
            int r = random.Next(0,255);
            int g = random.Next(0,255);
            int b = random.Next(0,255);
            Console.WriteLine($"Original: ({r}, {g}, {b})");
            //brighten the color
            transform = brighten;
            var bright = ApplyTransform(r, g, b, transform);
            Console.WriteLine($"Brightened: ({bright.Item1}, {bright.Item2}, {bright.Item3})");
            //darken the color
            transform = darken;
            var dark = ApplyTransform(r, g, b, transform);
            Console.WriteLine($"Darkened: ({dark.Item1}, {dark.Item2}, {dark.Item3})");
            //invert the color
            transform = invert;
            var inverted = ApplyTransform(r, g, b, transform);
            Console.WriteLine($"Inverted: ({inverted.Item1}, {inverted.Item2}, {inverted.Item3})");
            //wait for input to end program
            Console.ReadLine();
        }
    }
}
