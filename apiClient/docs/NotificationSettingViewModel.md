
# NotificationSettingViewModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**isEnabled** | **Boolean** |  |  [optional]
**assetId** | [**UUID**](UUID.md) |  |  [optional]
**managerId** | [**UUID**](UUID.md) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**conditionType** | [**ConditionTypeEnum**](#ConditionTypeEnum) |  |  [optional]
**conditionAmount** | **Double** |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
PLATFORMNEWSANDUPDATES | &quot;PlatformNewsAndUpdates&quot;
PLATFORMEMERGENCY | &quot;PlatformEmergency&quot;
PLATFORMOTHER | &quot;PlatformOther&quot;
PROFILEUPDATED | &quot;ProfileUpdated&quot;
PROFILEPWDUPDATED | &quot;ProfilePwdUpdated&quot;
PROFILEVERIFICATION | &quot;ProfileVerification&quot;
PROFILE2FA | &quot;Profile2FA&quot;
PROFILESECURITY | &quot;ProfileSecurity&quot;
TRADINGACCOUNTPWDUPDATED | &quot;TradingAccountPwdUpdated&quot;
PROGRAMNEWSANDUPDATES | &quot;ProgramNewsAndUpdates&quot;
PROGRAMENDOFPERIOD | &quot;ProgramEndOfPeriod&quot;
PROGRAMCONDITION | &quot;ProgramCondition&quot;
FUNDNEWSANDUPDATES | &quot;FundNewsAndUpdates&quot;
FUNDENDOFPERIOD | &quot;FundEndOfPeriod&quot;
FUNDREBALANCING | &quot;FundRebalancing&quot;
MANAGERNEWPROGRAM | &quot;ManagerNewProgram&quot;
SIGNALS | &quot;Signals&quot;


<a name="ConditionTypeEnum"></a>
## Enum: ConditionTypeEnum
Name | Value
---- | -----
EMPTY | &quot;Empty&quot;
PROFIT | &quot;Profit&quot;
LEVEL | &quot;Level&quot;
AVAILABLETOINVEST | &quot;AvailableToInvest&quot;



