package com.example.multithreading.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stats {
    private int files_deleted;
    private int files_added;
    private int folders_deleted;
    private int folders_added;

    public void sumWith(Stats other) {
        this.files_added += other.files_added;
        this.files_deleted += other.files_deleted;
        this.folders_added += other.folders_added;
        this.folders_deleted += other.folders_deleted;
    }
}
