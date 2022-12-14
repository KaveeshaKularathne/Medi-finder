package com.app.Medifinder.Entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import javax.persistence.*;
@Getter
@NoArgsConstructor
@Entity
@Table(name="\"User\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int user_id;

        private  String emailId;
        private String username;
        private String password;
        private String longitude;
        private String latitude;
        private String user_role;
        private boolean Enabled;



    public User() {}

        public User(int user_id, String emailId, String username, String password, String longitude, String latitude, String user_role) {
            super();
            this.user_id = user_id;
            this.emailId = emailId;
            this.username = username;
            this.password = password;
            this.longitude = longitude;
            this.latitude = latitude;
            this.user_role = user_role;



        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getEmailId() {
            return emailId;
        }

        public void setEmailId(String emailId) {
            this.emailId = emailId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getUser_role() {
            return user_role;
        }

        public void setUser_role(String user_role) {
            this.user_role = user_role;
        }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean enabled) {
        Enabled = enabled;
    }
}
