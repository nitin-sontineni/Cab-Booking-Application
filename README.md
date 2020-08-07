# Cab-Booking-Application

## Brief Description of the Application
This Online Cab Booking project deals with an online application designed for booking cabs as
per the requirements of the customers at their convenience. The customer side of the application
should have the ability to:

* Register as a new user with information such as user name, a user id (unique across the
system), phone number, and email id.
* An existing customer should log in into the system with user id and password.
* A customer has options to book a cab by entering details like pick-up point and drop-off
point.
* After the customer has requested a cab, the cab driver located nearest to the customer
will be assigned to him/her and a booking confirmation containing the details of the driver
like name, phone number and rating will be shown to the customer. Also, the estimated
fare and an approximate duration of the trip will be displayed to the customer.
* If more than one driver gets matched with a customer, then the driver with the maximum
rating will be assigned to the customer.
* A request timed out message will be shown to the customer if there are no drivers
available.
* Once the trip is complete the necessary money will be deducted from the customer’s
wallet. A wallet is associated with a customer that contains money that the customer can
use to pay for his/her ride. Here, it is assumed that a customer’s digital wallet associated
with the cab booking portal is the only acceptable mode of payment.
* There should also be an option to add more money to the digital wallet.
* A customer will have to maintain a minimum of 300 INR for making a booking request. If
the balance in the wallet is not sufficient, then the customer will have to first add money to
the wallet and then proceed with the booking.

Assume a set of location points which can be either a pickup point or a drop-off point for a
customer. Every location point may contain a set of customers C and a set of cab drivers D. The
set C and/or D can be empty for a location. Customers and drivers can be located only at these
pre-defined location points. Once a trip is booked both the driver and the user will be busy for the
duration of the trip and will not participate in any booking. The application should be able to
support multiple customers simultaneously.
