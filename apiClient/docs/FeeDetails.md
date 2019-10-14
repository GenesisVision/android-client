# FeeDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**amount** | **Double** |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]

<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVPROGRAMENTRY | &quot;GvProgramEntry&quot;
GVPROGRAMSUCCESS | &quot;GvProgramSuccess&quot;
GVPROGRAMSUCCESSSUM | &quot;GvProgramSuccessSum&quot;
GVFUNDENTRY | &quot;GvFundEntry&quot;
GVGMGVTHOLDERFEE | &quot;GvGmGvtHolderFee&quot;
MANAGERPROGRAMENTRY | &quot;ManagerProgramEntry&quot;
MANAGERPROGRAMSUCCESS | &quot;ManagerProgramSuccess&quot;
MANAGERPROGRAMSUCCESSSUM | &quot;ManagerProgramSuccessSum&quot;
MANAGERFUNDENTRY | &quot;ManagerFundEntry&quot;
MANAGERFUNDEXIT | &quot;ManagerFundExit&quot;
GVWITHDRAWAL | &quot;GvWithdrawal&quot;
MANAGERSIGNALMASTERSUCCESSFEE | &quot;ManagerSignalMasterSuccessFee&quot;
MANAGERSIGNALMASTERVOLUMEFEE | &quot;ManagerSignalMasterVolumeFee&quot;
GVSIGNALSUCCESSFEE | &quot;GvSignalSuccessFee&quot;
GVFUNDTRADE | &quot;GvFundTrade&quot;

<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USDT | &quot;USDT&quot;
XRP | &quot;XRP&quot;
BCH | &quot;BCH&quot;
LTC | &quot;LTC&quot;
DOGE | &quot;DOGE&quot;
BNB | &quot;BNB&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;
