class Graph:

    #create an empty graph with V vertices
    def __init__(self, V=0, E=0):
        self.total_vertices = V
        self.total_edges = E
        self.vertices = {}
        self.is_bidirectional = True

    #add an edge v-w
    def add_edge(self, v1, v2, cost=0):
        vertex1 = self.find_vertex_by_name(v1)
        vertex2 = self.find_vertex_by_name(v2)
        if vertex1 == None or vertex2 == None:
            return False
        e = Edge(vertex1, vertex2, cost)

        self.vertices[vertex1].append(vertex2)
        self.vertices[vertex2].append(vertex1)

        vertex1.add_edge(e)
        if self.is_bidirectional:
            vertex2.add_edge(e)

    def add_vertex(self, vertex_name):
        v = Vertex(vertex_name)
        if v not in self.vertices:
            self.vertices[v] = []

    def find_vertex_by_name(self, vertex_name):
        for v in self.vertices:
            if v.name == vertex_name:
                return v

    #vertices adjacent to v
    def adj(self,v):
        pass

    #returns number of vertices
    def V(self):
        pass

    #returns number of edges
    def E(self):
        pass

    #prints graphs
    def print(self):
        for v,adj in self.vertices.items():
            if(len(adj) > 0):
                for a_v in adj:
                    print(v.name," - ",a_v.name)
            else:
                print(v.name)

    


class Vertex:

    def __init__(self, name=None, cost=0):
        self.edges = []
        self.isVisited = False
        self.cost = cost
        self.name = name

    def add_edge(self, e):
        return self.edges.append(e)

    def get_edges(self):
        return self.edges

class Edge:

    def __init__(self, from_vertex, to_vertex, cost=0):
        self.to_vertex = to_vertex
        self.to_vertex = from_vertex
        self.cost = cost
        self.isVisited = False
        self.isBlocked = False
