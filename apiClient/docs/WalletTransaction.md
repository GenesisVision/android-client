
# WalletTransaction

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**amount** | **Double** |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**walletId** | [**UUID**](UUID.md) |  |  [optional]
**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  [optional]
**investmentProgram** | [**InvestmentProgramTxInfo**](InvestmentProgramTxInfo.md) |  |  [optional]
**investmentProgramRequest** | [**InvestmentProgramRequestTxInfo**](InvestmentProgramRequestTxInfo.md) |  |  [optional]
**paymentTx** | [**PaymentTxInfo**](PaymentTxInfo.md) |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
DEPOSIT | &quot;Deposit&quot;
WITHDRAW | &quot;Withdraw&quot;
OPENPROGRAM | &quot;OpenProgram&quot;
INVESTTOPROGRAM | &quot;InvestToProgram&quot;
WITHDRAWFROMPROGRAM | &quot;WithdrawFromProgram&quot;
PROFITFROMPROGRAM | &quot;ProfitFromProgram&quot;
CANCELINVESTMENTREQUEST | &quot;CancelInvestmentRequest&quot;
PARTIALINVESTMENTEXECUTIONREFUND | &quot;PartialInvestmentExecutionRefund&quot;
CLOSINGPROGRAMREFUND | &quot;ClosingProgramRefund&quot;


<a name="CurrencyEnum"></a>
## Enum: CurrencyEnum
Name | Value
---- | -----
UNDEFINED | &quot;Undefined&quot;
GVT | &quot;GVT&quot;
ETH | &quot;ETH&quot;
BTC | &quot;BTC&quot;
ADA | &quot;ADA&quot;
USD | &quot;USD&quot;
EUR | &quot;EUR&quot;



