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
    public class DataRequest
    {
        public DataRequest()
        {
        }

        public static Event BloombergRequest()//DateTime startDate, DateTime endDate)
        {
            bool result;
            bool done = false;
            // Create a SessionOptions object to hold the session parameters
            SessionOptions sessionOptions = new SessionOptions();
            // Since this program will run on the same PC as the Bloomberg software,
            //  we use “localhost” as the host name and port 8194 as the default port.
            sessionOptions.ServerHost = "localhost";
            sessionOptions.ServerPort = 8194;
            // Create a Session object using the sessionOptions
            Session session = new Session(sessionOptions);
            // Start the Session
            result = session.Start();
            // Open up the Reference data Service
            result = session.OpenService("//blp/refdata");
            // Get a reference to the service
            Service referenceService = session.GetService("//blp/refdata");
            // Create the Request object to represent the data request
            Request request = referenceService.CreateRequest("HistoricalDataRequest");
            // Specify the security we are interested in
            request.Append("securities", "IBM US Equity");
            // Specify the fields we are interested in 
            request.Append("fields", "PX_LAST");
            request.Append("fields", "PE_RATIO");
            // Set the start and ending dates and the max number of data points
            request.Set("startDate", "20130601");
            request.Set("endDate", "20130630");
            request.Set("maxDataPoints", 20);
            // Submit the request
            session.SendRequest(request, null);
            // Now we can process the incoming Events
            done = false;
            Event eventObject = null;
            while (!done)
            {
                // Grab the next Event object
                eventObject = session.NextEvent();
                // If this event type is Response then process the messages
                if (eventObject.Type == Event.EventType.RESPONSE)
                {
                    // Loop over all of the messages in this Event
                    foreach (Message msg in eventObject)
                    {
                        // For debugging, print entire message
                        //System.Console.WriteLine(msg);
                    }
                    done = true;
                } // End if event type is Response
            } // end while not done 
              // Pause so we can view the output
            //Console.ReadKey();
            // Close the session 
            session.Stop();

            return eventObject;
        }

    }
}
