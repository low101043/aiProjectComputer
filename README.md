# AI Project Nathaniel Lowis 2020.

This is an AI Project. 
It implements these Algorithms to a certain degree:
- Regression (Linear and Non linear and multivariable and univariate)
- Logistic Regression (Linear MultiVariable)
- Depth First Search
- Breadth First Search
- A* Search
- Q-Learning
- K Nearest Neighbour
- Ant Colony Optimisation

The Graph class used throughout requires that the nodes are numbered 0,..., n with n being the number of nodes - 1. 
Please only add one edge between 2 nodes as well

## Note:
This has not been fully tested.  This means that some inputs will not work as I have not fully tested the program.  

### Known/Suspected Problems
- For the search algorithms if there is not a path to the end node form the start node.  Also the route might not always be found
- Please do not use large amounts of data/ graphs this has not been optimised
- For Regression Algorithms I have not written all code which will allow the program to guess what class/number the input points to.  If I have written the code it is not accessible to the user at the moment
- There are a lot of TODO throughout the program.  These specifies areas that I believe need to be improved.

### Prerequistes:
- Java 11 or later (No guarantee that the code will work in any earlier Java Version)
- JavaFX on machine
- Logger4 on the machine




### Credits:
- Miqing Li, 
Harish Tayyar Madabushi,
Leandro Minku,
    - All three provided pseudocode for the different AI algorithms being implemented
    

- Alan Sexton
     - Provided starting code for reading CSV files and provided Code for MergeSort and Linear Search.  

- MrEbbinghaus https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx. 
    - Provided Code for ScreenChanger which I have then edited to make it work for my purposes

- Oracle Docs https://docs.oracle.com/javafx/2/ui_controls/text-field.htm
    - Provided Code for GUI which is used throughout.
