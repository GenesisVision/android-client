# PushNotificationViewModel

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | [**UUID**](UUID.md) |  |  [optional]
**text** | **String** |  |  [optional]
**type** | **String** | Notification type  &lt;br&gt;Platform&lt;br&gt;Profile&lt;br&gt;Asset&lt;br&gt;TradingAccount&lt;br&gt;User&lt;br&gt;Signal&lt;br&gt;Social&lt;br&gt;PlatformAsset  Enum: GenesisVision.Core.DataModel.Attributes.NotificationGroup |  [optional]
**date** | [**DateTime**](DateTime.md) |  |  [optional]
**imageUrl** | **String** |  |  [optional]
**isUnread** | **Boolean** |  |  [optional]
**location** | [**NotificationLocationViewModel**](NotificationLocationViewModel.md) |  |  [optional]
**assetDetails** | [**AssetDetails**](AssetDetails.md) |  |  [optional]
**userDetails** | [**ProfilePublic**](ProfilePublic.md) |  |  [optional]
**platformAssetDetails** | [**BasePlatformAsset**](BasePlatformAsset.md) |  |  [optional]
**channel** | [**PushNotificationChannel**](PushNotificationChannel.md) |  |  [optional]
