
# Period

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**number** | **Integer** |  |  [optional]
**dateFrom** | [**DateTime**](DateTime.md) |  |  [optional]
**dateTo** | [**DateTime**](DateTime.md) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**startBalance** | **Double** |  |  [optional]
**managerStartBalance** | **Double** |  |  [optional]
**managerStartShare** | **Double** |  |  [optional]
**processStatus** | [**ProcessStatusEnum**](#ProcessStatusEnum) |  |  [optional]
**investmentRequest** | [**List&lt;InvestmentProgramRequest&gt;**](InvestmentProgramRequest.md) |  |  [optional]


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
PLANNED | &quot;Planned&quot;
INPROCCESS | &quot;InProccess&quot;
CLOSED | &quot;Closed&quot;


<a name="ProcessStatusEnum"></a>
## Enum: ProcessStatusEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
ACCRUEPROFITSDONE | &quot;AccrueProfitsDone&quot;
REEVALUATEMANAGERTOKENDONE | &quot;ReevaluateManagerTokenDone&quot;
PROCESSINVESTMENTREQUESTSDONE | &quot;ProcessInvestmentRequestsDone&quot;
CLOSEPERIODDONE | &quot;ClosePeriodDone&quot;



