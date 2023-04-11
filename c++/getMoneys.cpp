#include<iostream>
#include<vector>
using namespace std;

vector<int> getMoneys(vector<pair<int,int>> & jobs,vector<int> & ability){
    int n = ability.size();
    sort(jobs.begin(),jobs.end(),
        [](const auto &x, const auto &y) {
                if (x.first != y.first) {
                    return x.first < y.first;
                }
                return x.second > y.second;
            });
    
    auto & pre = jobs[0]; 
    vector<pair<int,int>> v = {pre};
    for (int i = 1; i < jobs.size();i++){
        if (jobs[i].first != pre.first && jobs[i].second > pre.second){
            pre = jobs[i];
            v.push_back(pre);
        }
    }

  
    sort(ability.begin(),ability.end());
    auto it = v.rbegin();
    vector<int> ans(n);

    for (int i = n-1; i>=0;i--){
        while (it != v.rend() && it->first > ability[i]) it++;
        if (it-> first <= ability[i]){
             ans[i] = it->second;
        }
    }
    return ans;
    
}

int main() {
    vector<pair<int,int>> jobs = {{1,2},{3,4},{5,6},{7,8}};
    vector<int> ability = {2,4,6};
    vector<int> ans = getMoneys(jobs,ability);

    for (auto a:ans){
        cout << a << " ";
    }
}