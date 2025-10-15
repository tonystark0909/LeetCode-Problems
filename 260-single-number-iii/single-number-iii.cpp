class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        vector<int> res;
        unordered_map<int,int> umap;
        for (int i = 0; i < nums.size(); ++i)
        {
            umap[nums[i]]++;
        }
        for (unordered_map<int,int>::iterator iter = umap.begin();
            iter != umap.end(); ++iter)
        {
            if (iter->second == 1)
                res.push_back(iter->first);
        }
        return res;
    }
};