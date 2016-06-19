package com.magnus.edutech.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.magnus.edutech.activities.HomeScreenActivity;
import com.magnus.edutech.activities.PriceListScreenActivity;
import com.magnus.edutech.activities.ActivityShowVideosFromVimeo;
import com.magnus.edutech.activities.VideoPlayScreenActivity;
import com.magnus.edutech.R;
import com.magnus.edutech.model.Videos;
import com.magnus.edutech.view.utility.Utilities;
import com.magnus.edutech.App.GlobalConstants;

import org.json.JSONException;

import java.util.List;

/**
 * Created by Edwin on 28/02/2015.
 */
public class VideosFragmentRecycleViewAdapter extends RecyclerView.Adapter<VideosFragmentRecycleViewAdapter.ViewHolder> {

    // class
    private LoadDataInBackGround loadDataInBackGround;
    private RealPathUtil utilityClass;


    // xml
    // variables
    private List<Videos> ListOfVideos;
    private Context context;
    private Typeface typefaceContentMedium;
    private final int RC_REQUEST = 10001;
    private final String TAG = "MangusApp";
    HomeScreenActivity gridVideoActivity;

    public VideosFragmentRecycleViewAdapter(Context ctx, List<Videos> ListOfVideos) {
        super();
        this.ListOfVideos = ListOfVideos;
        context = ctx;
        typefaceContentMedium = Typeface.createFromAsset(context.getAssets(), GlobalConstants.FONT_PATH_LIGHT);
        loadDataInBackGround = new LoadDataInBackGround();
        utilityClass = new RealPathUtil(context);

        gridVideoActivity = (HomeScreenActivity) context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_adapter_fragement_videos_list,
                viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    /**
     * On Bind View Holder :
     *
     * @functionality : handle Grid View Click
     * @nameParameter : Name Of parameter shown on gridList
     * @iconParameter : Icon of parameter shown on gridList
     * @iconBackground : Background Icon for parameter shown on gridList.
     */

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Videos videos = ListOfVideos.get(position);

		/*if (position == 0 || position % 2 == 0) {
            viewHolder.llAdapterVideosItem
					.setBackgroundColor(context.getResources().getColor(R.color.colorChapterItemLayoutEven));
			;
		} else {
			viewHolder.llAdapterVideosItem.setBackgroundColor(context.getResources().getColor(R.color.white));
			;
		}*/

        viewHolder.VideoName.setText(videos.getName());


        if (videos.isPurchased()) {
            viewHolder.imageViewLock.setVisibility(View.GONE);
        } else {
            viewHolder.imageViewLock.setVisibility(View.VISIBLE);
        }


        /*viewHolder.imageViewVideoDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {
                int position = (Integer) view.getTag();
                Videos videos = ListOfVideos.get(position);

                buttonClickVideoDownload(view);
				if (videos.isPurchased()) {
					callForPurchasingItem(videos);
					
					 * GlobalConstants.showNoticeDialog("Information : ",
					 * "Please purchase our subject package", 1, context);
					 
				} else {
					buttonClickVideoDownload(view);
				}

            }
        });*/
        viewHolder.imageViewVideoPlay.setTag(position);
        viewHolder.imageViewVideoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)

            {
                int position = (Integer) view.getTag();
                Videos videos = ListOfVideos.get(position);

                if (videos.isPurchased()) {
                    buttonClickPlay(view);
                } else {
                    Intent intent = new Intent(context, PriceListScreenActivity.class);
                    context.startActivity(intent);
            }
            }
        });

    }

    @Override
    public int getItemCount() {
        return ListOfVideos.size();
    }

    /**
     * Its a inner class to RecyclerViewAdapter Class. This ViewHolder class
     * implements View.OnClickListener to handle click events. If the
     * itemType==1 ; it implies that the view is a single row_item with TextView
     * and ImageView. This ViewHolder describes an item view with respect to its
     * place within the RecyclerView. For every item there is a ViewHolder
     * associated with it .
     */

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout llAdapterVideosItem;

        ImageView imageViewLock, imageViewVideoPlay;

        TextView VideoName;

        public ViewHolder(View itemView) {
            super(itemView);

            llAdapterVideosItem = (LinearLayout) itemView.findViewById(R.id.llAdapterVideosItem);
            VideoName = (TextView) itemView.findViewById(R.id.VideoName);
            VideoName.setTypeface(typefaceContentMedium);
            imageViewLock = (ImageView) itemView.findViewById(R.id.imageViewLock);
            imageViewVideoPlay = (ImageView) itemView.findViewById(R.id.imageViewVideoPlay);

        }

        /**
         * This defines onClick for every item with respect to its position.
         *
         * @functionality : handle Grid View Click
         * @status : 1 - Active 0 - Not Active
         * @CHANGEORNOT : True - Changes false- No Changes
         * @Result : Updating ArrayList
         */

        @Override
        public void onClick(View v) {

        }
    }

    // Play video
    public void buttonClickPlay(View v) {
        int position = (Integer) v.getTag();
        String videoName = "video";
        Videos videos = ListOfVideos.get(position);
        if (videos != null) {
            videoName = videoName + videos.getSubject_id() + videos.getCategory_id() + videos.getVideo_id()
                    + GlobalConstants.DOWNLOAD_VIDEO_EXTENSION;
            String RootDir = Utilities.getVideoFolderLocation();

            String fullVideoPath = RootDir + videoName;

            System.out.println("chekc Video : " + fullVideoPath);
            if (utilityClass.isFileExist(fullVideoPath)) {
                Intent intent = new Intent(context, VideoPlayScreenActivity.class);
                intent.putExtra(GlobalConstants.URL, fullVideoPath);
                intent.putExtra(GlobalConstants.NAME, videos.getName());
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, ActivityShowVideosFromVimeo.class);
                intent.putExtra(GlobalConstants.URL, videos.getUrl());
                intent.putExtra(GlobalConstants.NAME, videos.getName());
                context.startActivity(intent);

            }
        } else {
            GlobalConstants.showNoticeDialog("Error : ", "Unable to play or download this video", 1, context);
        }

    }

    //
    public void buttonClickVideoDownload(View v) {
        int position = (Integer) v.getTag();
        String videoName = "video";
        Videos videos = ListOfVideos.get(position);
        if (videos != null) {
            videoName = videoName + videos.getSubject_id() + videos.getCategory_id() + videos.getVideo_id()
                    + GlobalConstants.DOWNLOAD_VIDEO_EXTENSION;
            String RootDir = Utilities.getVideoFolderLocation();

            String fullVideoPath = RootDir + videoName;

            System.out.println("chekc Video : " + fullVideoPath);
            if (utilityClass.isFileExist(fullVideoPath)) {
                GlobalConstants.showNoticeDialog("Video Download ", "You had already downloaded this video.", 1,
                        context);
            } else {

                videos.setMessage("Downloading Videos....");
                try {
                    loadDataInBackGround.downloadVideoFromServer(context, videos);
                } catch (NumberFormatException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        } else {
            GlobalConstants.showNoticeDialog("Error : ", "Unable to play or download this video", 1, context);
        }

    }

}