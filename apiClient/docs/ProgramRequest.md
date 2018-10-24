
# ProgramRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**programId** | [**UUID**](UUID.md) |  |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**value** | **Double** |  |  [optional]
**fundWithdrawPercent** | **Double** | Used only in fund withdraw request |  [optional]
**type** | [**TypeEnum**](#TypeEnum) |  |  [optional]
**status** | [**StatusEnum**](#StatusEnum) |  |  [optional]
**logo** | **String** |  |  [optional]
**title** | **String** |  |  [optional]
**color** | **String** |  |  [optional]
**canCancelRequest** | **Boolean** |  |  [optional]
**programType** | [**ProgramTypeEnum**](#ProgramTypeEnum) |  |  [optional]


<a name="TypeEnum"></a>
## Enum: TypeEnum
Name | Value
---- | -----
INVEST | &quot;Invest&quot;
WITHDRAWAL | &quot;Withdrawal&quot;


<a name="StatusEnum"></a>
## Enum: StatusEnum
Name | Value
---- | -----
NEW | &quot;New&quot;
EXECUTED | &quot;Executed&quot;
CANCELLED | &quot;Cancelled&quot;


<a name="ProgramTypeEnum"></a>
## Enum: ProgramTypeEnum
Name | Value
---- | -----
PROGRAM | &quot;Program&quot;
FUND | &quot;Fund&quot;



