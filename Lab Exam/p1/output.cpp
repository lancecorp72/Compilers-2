#include<iostream>
#include<stack>
#include <unistd.h>
#include<bitset>
using namespace std;

int main()
{
	int ary[256];
	stack<int> stk;
	int aryPtr=0;
	ary[aryPtr]=65535;
	cout<<hex<<ary[aryPtr]<<dec<<endl;
	cout<<bitset<16>(ary[aryPtr])<<endl;
	cout<<ary[aryPtr]<<endl;
	return 0;
}

