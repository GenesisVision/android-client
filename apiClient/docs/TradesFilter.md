
# TradesFilter

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**investmentProgramId** | [**UUID**](UUID.md) |  |  [optional]
**dateFrom** | [**DateTime**](DateTime.md) |  |  [optional]
**dateTo** | [**DateTime**](DateTime.md) |  |  [optional]
**symbol** | **String** |  |  [optional]
**sorting** | [**SortingEnum**](#SortingEnum) |  |  [optional]
**skip** | **Integer** |  |  [optional]
**take** | **Integer** |  |  [optional]


<a name="SortingEnum"></a>
## Enum: SortingEnum
Name | Value
---- | -----
BYDATEASK | &quot;ByDateAsk&quot;
BYDATEDESC | &quot;ByDateDesc&quot;
BYTICKETASK | &quot;ByTicketAsk&quot;
BYTICKETDESC | &quot;ByTicketDesc&quot;
BYSYMBOLASK | &quot;BySymbolAsk&quot;
BYSYMBOLDESC | &quot;BySymbolDesc&quot;
BYDIRECTIONASK | &quot;ByDirectionAsk&quot;
BYDIRECTIONDESC | &quot;ByDirectionDesc&quot;



