#include <cassert>
#include <map>
#include <set>
#include <list>
#include <cmath>
#include <ctime>
#include <deque>
#include <queue>
#include <stack>
#include <string>
#include <bitset>
#include <cstdio>
#include <limits>
#include <vector>
#include <climits>
#include <cstring>
#include <cstdlib>
#include <fstream>
#include <numeric>
#include <sstream>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <iomanip>
#include <array>
using namespace std;

// This is the class you need to implement! Feel free to add members, private methods etc, but don't change the public
// method signatures.
struct node{
    uint64_t t ;
    int v;
    double p ;
};
class RiskLimitProcessor
{
    public:
        map<string,pair<double,pair<int,double>>>mp;
        map<string,vector<node>>mpv;
        void AddLimit(const std::string& instrument, double maxValue, int maxVolume10Seconds, double maxValue1Second)
        {
            mp[instrument] = {maxValue,{maxVolume10Seconds,maxValue1Second}};
        }

        void ProcessOrder(const std::string& instrument, uint64_t timestamp, int volume, double price)
        {
            if(!mp.count(instrument))
            {
                std::cout << "NO_LIMITS " <<instrument <<  std::endl;
                return ;
            }
             mpv[instrument].push_back({timestamp,volume,price});
            int total_volume = 0;
            double total_value = 0;

            if(volume * price > mp[instrument].first)
            {
                cout << "MAX_VAL_LIMIT " << instrument<<endl;
                return ;
            }
            auto v = mpv[instrument];
            for(int i = v.size()-1;i>=0;i--){
                auto j  = v[i];
                if( timestamp - j.t <= 9999)    total_volume += j.v;
                if( timestamp - j.t <= 999)     total_value += volume*price;
                if(total_volume > mp[instrument].second.first)  {
                    cout <<"MAX_VOL_10S_LIMIT " << instrument << endl;
                    return ;
                }
            }
            if(total_value > mp[instrument].second.second){
                cout <<"MAX_VAL_1S_LIMIT " << instrument << endl;
                    return ;
            }
        }
};

int main()
{
    RiskLimitProcessor riskLimitProcessor;  
    while(!std::cin.eof())
    {
        std::string action, instrument;
        std::cin >> action >> instrument;
        if (action.empty())
            break; // handle whitespace at end of input
        if (action == "LIMIT")
        {
            double maxValue;
            int maxVolume10Seconds;
            double maxValue1Second;
            std::cin >> maxValue >> maxVolume10Seconds >> maxValue1Second;
            riskLimitProcessor.AddLimit(instrument, maxValue, maxVolume10Seconds, maxValue1Second);
        }
        else if (action == "ORDER")
        {
            uint64_t timestamp;
            int volume;
            double price;
            std::cin >> timestamp >> volume >> price;
            riskLimitProcessor.ProcessOrder(instrument, timestamp, volume, price);
        }
        else
        {
            std::cerr << "Malformed input!\n";
            return -1;
        }
    }
    return 0;
}/*

LIMIT ABC 100.0 1000 100.0
ORDER ABC 10000000 10 8.0
ORDER ABC 10000300 10 8.0
*/