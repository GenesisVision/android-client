# SignalAccountDetails

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**tags** | [**List&lt;ProgramTag&gt;**](ProgramTag.md) |  |  [optional]
**id** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**description** | **String** |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**creationDate** | [**DateTime**](DateTime.md) |  |  [optional]
**manager** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**chart** | [**List&lt;ChartSimple&gt;**](ChartSimple.md) |  |  [optional]

<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
NONE | &quot;None&quot;
PENDING | &quot;Pending&quot;
ERRORCREATING | &quot;ErrorCreating&quot;
ACTIVE | &quot;Active&quot;
CLOSED | &quot;Closed&quot;
ARCHIVED | &quot;Archived&quot;
CLOSEDDUETOINACTIVITY | &quot;ClosedDueToInactivity&quot;
