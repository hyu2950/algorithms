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

struct Dividend
{
    uint64_t amount = 0;
    uint64_t days = 0;
};
using namespace std;
const int N = 2000010;
long long tr[N];
int lowbit(int x)
{
    return x&-x;
}
long long query(int x){
    long long ans = 0;
    for(int i =x;i;i-=lowbit(i))    ans += tr[i];
    return ans;
}
void add(int x,int d){
    for(int i=x;i<N;i+=lowbit(i))
        tr[i] += d;
}
class FuturePricingEngine
{
public:
    long long ans = 0;
    vector<Dividend>v;
    FuturePricingEngine(uint64_t stockPrice, std::vector<Dividend> dividends)
    {
        ans = stockPrice;
        v =  dividends;
        for(Dividend x : v)
            add(x.days,x.amount);
        
        /* implement constructor */
    }

    void UpdateDividend(uint64_t dividendId, Dividend updatedDividend)
    {
        /* write code here */
        dividendId--;
        int x = v[dividendId].amount;
        int d = v[dividendId].days;
        add(d,-x);
        v[dividendId] = updatedDividend;
        x = v[dividendId].amount;
        d = v[dividendId].days;
        add(d,x);
    }

    int64_t CalculateFuturePrice(uint64_t daysToFuture)
    {
        return ans - query(daysToFuture);
    }
};

int main() {

    uint64_t S, N, Q;
    std::cin >> S >> N >> Q;

    std::vector<Dividend> dividends(N);
    for (uint64_t i = 0; i < N; ++i)
    {
        std::cin >> dividends[i].amount >> dividends[i].days;
    }

    FuturePricingEngine engine(S, dividends);

    for (uint64_t q = 0; q < Q; ++q)
    {
        std::string queryType;
        std::cin >> queryType;
        if (queryType == "DIVIDEND_UPDATE")
        {
            uint64_t dividendId;
            Dividend updatedDividend;
            std::cin >> dividendId >> updatedDividend.amount >> updatedDividend.days;
            engine.UpdateDividend(dividendId, updatedDividend);
        }
        else if (queryType == "PRICE")
        {
            uint64_t daysToFuture;
            std::cin >> daysToFuture;
            int64_t futurePrice = engine.CalculateFuturePrice(daysToFuture);
            std::cout << futurePrice << std::endl;
        }
        else
        {
            std::cerr << "Malformed input!\n";
            return -1;
        }
    }
    return 0;
}