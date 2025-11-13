# MST Edge Removal and Reconnection

A Java program that builds a Minimum Spanning Tree, removes an edge, and finds a replacement edge to reconnect the tree.

## What This Program Does

1. Builds a Minimum Spanning Tree (MST) using Kruskal's algorithm
2. Shows all edges in the MST
3. Removes one edge from the MST
4. Shows the two disconnected components
5. Finds a replacement edge to reconnect the tree
6. Shows the new MST with the replacement edge

## Requirements

- Java 11 or higher
- Maven 3.6 or higher

## How to Run

### Clone the repository
```bash
git clone https://github.com/yourusername/DAABonus.git
cd DAABonus
```

### Run the program
```bash
mvn clean compile exec:java -Dexec.mainClass="com.mst.MSTManager"
```

### Or create a JAR file and run it
```bash
mvn clean package
java -jar target/DAABonus-1.0.0-jar-with-dependencies.jar
```

## Example Output
MST

Graph created with 7 vertices and 11 edges
Building MST
Original MST Edges:
  1. (5 - 6, weight = 1)
  2. (0 - 1, weight = 2)
  3. (4 - 6, weight = 2)
  4. (1 - 2, weight = 3)
  5. (3 - 5, weight = 4)
  6. (1 - 4, weight = 5)
Total MST Weight: 17
Removing an edge from MST
Removing edge: (4 - 6, weight = 2)

After removing:
Number of components: 2
Component 1: {0, 1, 2, 4}
Component 2: {3, 5, 6}

Replacing
Replacement edge found: (0 - 3, weight = 6)

New MST after reconnection:
  1. (5 - 6, weight = 1)
  2. (0 - 1, weight = 2)
  3. (1 - 2, weight = 3)
  4. (3 - 5, weight = 4)
  5. (1 - 4, weight = 5)
  6. (0 - 3, weight = 6) [NEW EDGE ADDED]
New Total MST Weight: 21
Weight difference: +4

## Project Structure

```
DAABonus/
├── src/main/java/com/mst/
│   ├── Edge.java          - Represents edges in the graph
│   ├── UnionFind.java     - Used for cycle detection
│   ├── Graph.java         - Main graph operations
│   └── MSTManager.java    - Main program
├── pom.xml                - Maven configuration
└── README.md
```

## How to Modify the Graph

Open `MSTManager.java` and edit the `createSampleGraph()` method:

```java
Graph graph = new Graph(7);  // 7 vertices
graph.addEdge(0, 1, 2);      // edge from vertex 0 to 1 with weight 2
graph.addEdge(1, 2, 3);      // add more edges...
```

To change which edge gets removed, modify this line:
```java
int edgeToRemove = 2;  // removes the edge at index 2
```

## Algorithm Used

**Kruskal's Algorithm** with Union-Find data structure:
- Sorts edges by weight
- Adds edges one by one if they don't create a cycle
- Uses Union-Find to efficiently detect cycles

**Time Complexity**: O(E log E) where E = number of edges

## Common Issues

**Maven not found?**
- Install Maven from: https://maven.apache.org/download.cgi

**Wrong Java version?**
- Make sure you have JDK 11 or higher

**Program won't run?**
- First run: `mvn clean compile`

## License

Open source - free to use for educational purposes.
