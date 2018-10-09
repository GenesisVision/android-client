
# NotificationViewModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**text** | **String** |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**programId** | [**UUID**](UUID.md) |  |  [optional]
**managerId** | [**UUID**](UUID.md) |  |  [optional]
**logo** | **String** |  |  [optional]
**url** | **String** |  |  [optional]
**isUnread** | **Boolean** |  |  [optional]


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
PROGRAMNEWSANDUPDATES | &quot;ProgramNewsAndUpdates&quot;
PROGRAMENDOFPERIOD | &quot;ProgramEndOfPeriod&quot;
PROGRAMCONDITION | &quot;ProgramCondition&quot;
MANAGERNEWPROGRAM | &quot;ManagerNewProgram&quot;



