class Solution {
public:
    int find(int x, vector<int>& parent) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x], parent);
    } // path compression

    void unionset(int a, int b, vector<int>& parent) {
        int boss_a = find(a, parent);
        int boss_b = find(b, parent);
        parent[boss_a] = boss_b;
    }

    vector<int> processQueries(int c, vector<vector<int>>& connections,
                               vector<vector<int>>& queries) {
        vector<int> res;
        vector<bool> is_online(c + 1, true); // record state
        vector<int> parent(c + 1, 0); // DSU
        // <root, heap of root>
        map<int, priority_queue<int, vector<int>, greater<int>>> group_heaps; 
         
        //init
        for (int i = 0; i <= c; ++i) {
            parent[i] = i;
        } 

        // create connection
        for (int i = 0; i < connections.size(); ++i) {
            unionset(connections[i][0], connections[i][1], parent);
        }

        // built heap
        for (int i = 1; i <= c; ++i) {
            int root = find(i, parent);
            group_heaps[root].push(i);
        }

        for (int i = 0; i < queries.size(); ++i) {
            // Type [2, x]
            if (queries[i][0] == 2) { 
                is_online[queries[i][1]] = false;
            } 
            // Type [1, x]
            else { 
                // online
                if (is_online[queries[i][1]] == true) {
                    res.push_back(queries[i][1]);
                }
                // offline
                else {
                    int root = find(queries[i][1], parent);
                    auto& heap_r = group_heaps[root];
                    while (!heap_r.empty() &&
                           is_online[heap_r.top()] == false) {
                        heap_r.pop();
                    }
                    if (heap_r.empty()) {
                        res.push_back(-1);
                    } else {
                        res.push_back(heap_r.top());
                    }
                }
            }
        }
        return res;
    }
};