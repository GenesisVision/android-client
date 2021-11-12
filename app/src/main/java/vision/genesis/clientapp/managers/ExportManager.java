package vision.genesis.clientapp.managers;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import org.joda.time.DateTime;

import java.util.UUID;

import io.swagger.client.api.ProgramsApi;
import io.swagger.client.model.Timeframe;
import vision.genesis.clientapp.BuildConfig;
import vision.genesis.clientapp.R;
import vision.genesis.clientapp.model.DateRange;
import vision.genesis.clientapp.utils.DateTimeUtil;

/**
 * GenesisVisionAndroid
 * Created by Vitaly on 12/11/2021.
 */

public class ExportManager
{
	private Context context;

	private ProgramsApi programsApi;

	public ExportManager(Context context, ProgramsApi programsApi) {
		this.context = context;
		this.programsApi = programsApi;
	}

	public void exportFinancialStatistics(UUID programId, String programName, DateRange dateRange, Timeframe timeframe) {
		StringBuilder sb = new StringBuilder();
		sb.append(BuildConfig.API_ADDRESS);
		sb.append("v2.0/programs/").append(programId.toString());
		sb.append("/periods/export/financialstatistic?");
		if (dateRange != null) {
			sb.append("&DateFrom=").append(dateRange.getFrom().toString());
			sb.append("&DateTo=").append(dateRange.getTo().toString());
		}
		if (timeframe != null) {
			sb.append("&Timeframe=").append(timeframe.toString());
		}
		downloadFile(sb.toString(), getFileName(programName, "financial_statistic"));
	}

	public void exportAnalytics(UUID programId, String programName, DateRange dateRange, Timeframe timeframe) {
		StringBuilder sb = new StringBuilder();
		sb.append(BuildConfig.API_ADDRESS);
		sb.append("v2.0/programs/").append(programId.toString());
		sb.append("/periods/export/analytics?");
		if (dateRange != null) {
			sb.append("&DateFrom=").append(dateRange.getFrom().toString());
			sb.append("&DateTo=").append(dateRange.getTo().toString());
		}
		if (timeframe != null) {
			sb.append("&Timeframe=").append(timeframe.toString());
		}
		downloadFile(sb.toString(), getFileName(programName, "analytics"));
	}

	public void exportPeriods(UUID programId, String programName, DateRange dateRange) {
		StringBuilder sb = new StringBuilder();
		sb.append(BuildConfig.API_ADDRESS);
		sb.append("v2.0/programs/").append(programId.toString());
		sb.append("/periods/export?");
		if (dateRange != null) {
			sb.append("&DateFrom=").append(dateRange.getFrom().toString());
			sb.append("&DateTo=").append(dateRange.getTo().toString());
		}
		downloadFile(sb.toString(), getFileName(programName, "periods"));
	}

	public void exportReports(UUID programId, String programName, DateRange dateRange, Timeframe timeframe) {
		StringBuilder sb = new StringBuilder();
		sb.append(BuildConfig.API_ADDRESS);
		sb.append("v2.0/programs/").append(programId.toString());
		sb.append("/periods/export/investorreport?");
		if (dateRange != null) {
			sb.append("&DateFrom=").append(dateRange.getFrom().toString());
			sb.append("&DateTo=").append(dateRange.getTo().toString());
		}
		if (timeframe != null) {
			sb.append("&Timeframe=").append(timeframe.toString());
		}
		downloadFile(sb.toString(), getFileName(programName, "investorreport"));
	}

	public void exportProgramTrades(UUID programId, String programName, DateRange dateRange) {
		StringBuilder sb = new StringBuilder();
		sb.append(BuildConfig.API_ADDRESS);
		sb.append("v2.0/programs/").append(programId.toString());
		sb.append("/trades/export?");
		if (dateRange != null) {
			sb.append("&DateFrom=").append(dateRange.getFrom().toString());
			sb.append("&DateTo=").append(dateRange.getTo().toString());
		}
		downloadFile(sb.toString(), getFileName(programName, "trades"));
	}

	public void exportFollowTrades(UUID programId, String followName, DateRange dateRange) {
		StringBuilder sb = new StringBuilder();
		sb.append(BuildConfig.API_ADDRESS);
		sb.append("v2.0/tradingaccount/").append(programId.toString());
		sb.append("/trades/export?");
		if (dateRange != null) {
			sb.append("&DateFrom=").append(dateRange.getFrom().toString());
			sb.append("&DateTo=").append(dateRange.getTo().toString());
		}
		downloadFile(sb.toString(), getFileName(followName, "trades"));
	}

	public void exportReferralHistory(DateRange dateRange) {
		StringBuilder sb = new StringBuilder();
		sb.append(BuildConfig.API_ADDRESS);
		sb.append("v2.0/partnership/rewards/history/export?");
		if (dateRange != null) {
			sb.append("&DateFrom=").append(dateRange.getFrom().toString());
			sb.append("&DateTo=").append(dateRange.getTo().toString());
		}
		downloadFile(sb.toString(), getFileName("rewards", "history"));
	}

	private String getFileName(String programName, String type) {
		return programName.concat("_").concat(type).concat("_").concat(DateTimeUtil.formatDateTimeFile(DateTime.now())).concat(".xlsx");
	}

	private void downloadFile(String url, String fileName) {
		DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
		if (downloadManager != null) {
			Uri uri = Uri.parse(url);
			DownloadManager.Request request = new DownloadManager.Request(uri);
			request.setTitle(fileName);
			request.setDescription(context.getString(R.string.downloading_file));
			request.addRequestHeader("Authorization", AuthManager.token.getValue() != null ? AuthManager.token.getValue() : "");
			request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
			request.setDestinationInExternalPublicDir(
					Environment.DIRECTORY_DOWNLOADS,
					fileName
			);
			downloadManager.enqueue(request);
		}
	}
}