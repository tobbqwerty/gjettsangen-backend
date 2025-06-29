package com.ezdevz.gjett_sangen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "video_views")
public class VideoView {

    @EmbeddedId
    private VideoViewId id;

    // Kobling mot Video
    @MapsId("videoId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "video_id", nullable = false, updatable = false)
    private Video video;

    // Kobling mot User
    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @Column(name = "view_count", nullable = false)
    private int viewCount = 0;

    public VideoView() {}

    public VideoView(User user, Video video) {
        this.user = user;
        this.video = video;
        this.id = new VideoViewId(user.getId(), video.getId());
    }

    public VideoViewId getId() { return id; }
    public void setId(VideoViewId id) { this.id = id; }

    public Video getVideo() { return video; }
    public void setVideo(Video video) { this.video = video; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public int getViewCount() { return viewCount; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }
}
