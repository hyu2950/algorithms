#include<iostream>
#include<map>

using namespace std;

// 写 hashmap setAl功能，要求o(1) 复杂度
// setAll 把所有value 都变成同一个值 
class MyHashMap{
    public:
        map<int,pair<int,int>> mp;
        int cur_time = 0;
        int setAll_time = -1;
        int setAll_value = -1;

        void put(int key, int value){
            mp[key] = {value,cur_time++};
        }
        
        void setAll(int value){
            setAll_time = cur_time++;
            setAll_value = value;
        }

        int get(int key){
            if (!mp.count(key)){
                return -1;
            }
            if (mp[key].second < setAll_time){
                return setAll_value;
            } else {
                return mp[key].first;
            }
        }
}; // there should have a ";" at the end of class