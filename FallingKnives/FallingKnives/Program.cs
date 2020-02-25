using System;
using System.Collections;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
// Bloomberg API Namespaces
using Bloomberglp.Blpapi;
// Define EventHanlder unambiguously associated with the Bloomberg event handler
// using EventHandler = Bloomberglp.Blpapi.EventHandler;
// Bloomberg Emulator Namespaces  https://bemu.codeplex.com/
using BEmu;
//using EventHandler = BEmu.EventHandler;

namespace FallingKnives
{
    class Program
    {
        static void Main(string[] args)
        {
            Event testEvent = DataRequest.BloombergRequest();
            if (testEvent.Type == Event.EventType.RESPONSE)
            {
                // Loop over all of the messages in this Event
                foreach (Message msg in testEvent)
                {
                    // For debugging, print entire message
                    System.Console.WriteLine(msg);
                }
            } // End if event type is Response
            Console.ReadKey();
        }
    }
}
