package io.swagger.client.api;

import java.util.Map;
import java.util.UUID;

import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface NotificationsApi
{
	/**
	 * @param authorization JWT access token (required)
	 * @param skip          (optional)
	 * @param take          (optional)
	 * @return Call&lt;Void&gt;
	 */
	@GET("v1.0/notifications")
	Observable<Void> v10NotificationsGet(
			@retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("skip") Integer skip, @retrofit2.http.Query("take") Integer take
	);

	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Integer&gt;
	 */
	@GET("v1.0/notifications/new")
	Observable<Integer> v10NotificationsNewGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("v1.0/notifications/settings")
	Observable<Void> v10NotificationsSettingsGet(
			@retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @param model         (optional)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/notifications/settings/program/{id}/add")
	Observable<Void> v10NotificationsSettingsProgramByIdAddPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization, @retrofit2.http.Query("model") Map<String, String> model
	);

	/**
	 * @param id            (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@GET("v1.0/notifications/settings/program/{id}")
	Observable<Void> v10NotificationsSettingsProgramByIdGet(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param id            (required)
	 * @param ntfId         (required)
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/notifications/settings/program/{id}/remove/{ntfId}")
	Observable<Void> v10NotificationsSettingsProgramByIdRemoveByNtfIdPost(
			@retrofit2.http.Path("id") UUID id, @retrofit2.http.Path("ntfId") UUID ntfId, @retrofit2.http.Header("Authorization") String authorization
	);

	/**
	 * @param authorization JWT access token (required)
	 * @return Call&lt;Void&gt;
	 */
	@POST("v1.0/notifications/settings/update")
	Observable<Void> v10NotificationsSettingsUpdatePost(
			@retrofit2.http.Header("Authorization") String authorization
	);

}
