package com.example.multithreading.entity;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.concurrent.atomic.AtomicInteger;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stats {
    @Builder.Default
    private AtomicInteger files_deleted = new AtomicInteger(0);

    @Builder.Default
    private AtomicInteger files_added = new AtomicInteger(0);

    @Builder.Default
    private AtomicInteger folders_deleted = new AtomicInteger(0);

    @Builder.Default
    private AtomicInteger folders_added = new AtomicInteger(0);

    // Atomic setters
    public void setFilesDeleted(int value) {
        files_deleted.set(value);
    }

    public void setFilesAdded(int value) {
        files_added.set(value);
    }

    public void setFoldersDeleted(int value) {
        folders_deleted.set(value);
    }

    public void setFoldersAdded(int value) {
        folders_added.set(value);
    }

    // Atomic getters
    public int getFilesDeleted() {
        return files_deleted.get();
    }

    public int getFilesAdded() {
        return files_added.get();
    }

    public int getFoldersDeleted() {
        return folders_deleted.get();
    }

    public int getFoldersAdded() {
        return folders_added.get();
    }

    // Thread-safe addition of another Stats object
    public void sumWith(Stats other) {
        this.files_added.addAndGet(other.files_added.get());
        this.files_deleted.addAndGet(other.files_deleted.get());
        this.folders_added.addAndGet(other.folders_added.get());
        this.folders_deleted.addAndGet(other.folders_deleted.get());
    }
}
