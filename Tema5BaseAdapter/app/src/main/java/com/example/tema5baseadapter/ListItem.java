package com.example.tema5baseadapter;

public class ListItem {

        private int imageResId;
        private String title;
        private String content;

        public ListItem(int imageResId, String tittle, String content) {
            this.imageResId = imageResId;
            this.title = title;
            this.content = content;

        }

        public int getImageResId() {
            return imageResId;
        }

        public String getTittle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }

