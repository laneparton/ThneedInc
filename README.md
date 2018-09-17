# ThneedInc
This was a senior project assigned in I400 - Application Development

## Assignment Part 1
Create a program that allows a business (Thneed Inc.) to keep track of customers and orders. There will be Customer objects that will indicate the name, address and phone number of the customer, along with a list of current and past orders. There will be Order objects which indicate which Customer is placing the order, and the details of the order, such as number of Thneeds, size(s) and color(s). The Order object should also indicate the date the order was placed and the date the order was filled (null if not yet filled). There will be a GUI which will allow the user to view a list of all orders, with the details of the currently selected order shown. If the user clicks on the Customer field for the current order, the details of the Customer should be shown. The GUI should allow the user to enter a new order, new customer, and update the date a particular order was filled. Finally, the GUI should allow the user to save the current state of the data (Orders and Customers) to a file. When the application launches, it should look for that file and populate the application with the information in that file if it exists.

## Assignment Part 2
You will have been given another team’s code for phase 1 and will need to extend it. You will first want to examine the code base and identify any problems and fix them (e.g. was the unit testing complete, were there any bugs, etc…) You may not simply replace the code base with your own code. General architectural decisions must remain the same. 
 
The extension will be for the program to keep track of inventory (current and projected), so the system can automatically indicate to a customer how long until their order is filled. The times in the application should use the actual system time. The following changes are anticipated:
● A new Inventory class which is updated whenever an order is filled or new inventory arrives at the warehouse. It should also keep track of scheduled inventory replacements so an estimated date can be provided for an item on backorder.
● A new field in the Order class, which indicates projected fill date.
● An addition to the GUI which displays the current inventory (separate out by size and colors), and allows the user to indicate when new Thneeds will be added to the warehouse inventory. As new inventory arrives (when the system time reaches the arrival date) and as orders are filled, the display should be adjusted accordingly.
● The program should also include a feature to generate reports that indicate how quickly orders are filled, which items are most/least popular, and which items get backordered. The report should essentially help the business plan their inventory better in the future based on the past. 
 
