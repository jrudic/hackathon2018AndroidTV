/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package rs.hydra.androidtv.hub;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public final class HubList {
    public static final String HUB_CATEGORY[] = {
            "QUIZZES",
            "BOARD GAMES",
            "KIDS",
            "BOOKS",
    };

    private static List<HubItem> list;
    private static long count = 0;

    public static List<HubItem> getList(String category) {
        list = setupItems(category);

        return list;
    }

    public static List<HubItem> setupItems(String category) {
        list = new ArrayList<>();

        String title[] = getTitlesByCategory(category);


        String description = "Kviz je vrsta društvene igre zasnovane na ispitivanju znanja i vestina iz različitih područja, najčešće takmičarskog karaktera. U nekim državama kviz je vrsta testa u obrazovanju kao pokazatelj znanja, vestina ili sposobnosti.\n" +
                "\n" +
                "Pub-kvizovi\n" +
                "Ova vrsta kvizova popularna je na zapadu, naročito u engleskim pubovima (Pub quiz, Quiz Nights ili Trivia nights). Studija iz 2009. procjenjuje broj kvizova na 22.445 tjedno u UK. U njima sudjeluju takmičari u timovima, a cilj je skupljanje poena. Nagrade mogu biti različite, kao na primjer sanduk pića, razni vaučeri, a ako se za sudjelovanje u kvizu plaća kotizacija, nagrade mogu biti i novčane.\n" +
                "\n" +
                "Za razliku od UK, u SAD je koncept pub-kvizova komercijaliziran pa tako više kviz-kompanija organiziraju kvizove u barovima i restoranima, a sudjelovanje se redovito plaća.";


        String studio[] = getStudioByCategory(category);

        String videoUrl[] = {
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
                "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4"
        };

        String bgImageUrl[] = getCardBacgroundImagesByCategory(category);
        String cardImageUrl[] = getCardImagesByCategory(category);

        for (int index = 0; index < title.length; ++index) {
            list.add(
                    buildHubItemInfo(
                            title[index],
                            description,
                            studio[index],
                            videoUrl[index],
                            cardImageUrl[index],
                            bgImageUrl[index], category));
        }

        return list;
    }

    private static String[] getTitlesByCategory(String category) {
        if (TextUtils.equals(category, HUB_CATEGORY[0])) {
            String quizTitles[] = {
                    "Home Pub Quiz",
                    "Slagalica",
                    "Conquiztador",
                    "Quiz Four...",
                    "Milioner"
            };
            return quizTitles;
        } else if (TextUtils.equals(category, HUB_CATEGORY[1])) {
            String boardTitles[] = {
                    "Catana",
                    "Puerto Rico",
                    "Monopoly",
                    "Dominion",
                    "Ticket To Ride"
            };
            return boardTitles;
        } else if (TextUtils.equals(category, HUB_CATEGORY[2])) {
            String kidsTitles[] = {
                    "Catana",
                    "Puerto Rico",
                    "Monopoly",
                    "Dominion",
                    "Ticket To Ride"
            };
            return kidsTitles;
        } else if (TextUtils.equals(category, HUB_CATEGORY[2])) {
            String kidsTitles[] = {
                    "Catana",
                    "Puerto Rico",
                    "Monopoly",
                    "Dominion",
                    "Ticket To Ride"
            };
            return kidsTitles;
        } else {
            String booksTitle[] = {
                    "Do Androids Dream of Electric Sheep",
                    "Everything I Never Told You by Celeste Ng.",
                    "The Electric Kool-Aid Acid Test by Tom Wolfe.",
                    "Are You There, Vodka?",
                    "The Devil Wears Prada by Lauren Weisberger."
            };
            return booksTitle;
        }
    }

    private static String[] getStudioByCategory(String category) {
        if (TextUtils.equals(category, HUB_CATEGORY[0])) {
            String quizStudio[] = {
                    "Hydra Quizzes", "Hydra Quizzes", "Hydra Quizzes", "Hydra Quizzes", "Hydra Quizzes"
            };
            return quizStudio;
        } else if (TextUtils.equals(category, HUB_CATEGORY[1])) {
            String boardGamesStudio[] = {
                    "Hydra Board Games", "Hydra Board Games", "Hydra Board Games", "Hydra Board Games", "Hydra Board Games"
            };
            return boardGamesStudio;
        } else if (TextUtils.equals(category, HUB_CATEGORY[2])) {
            String kidsGamesStudio[] = {
                    "Hydra for kids", "Hydra for kids", "Hydra for kids", "Hydra for kids", "Hydra for kids"
            };
            return kidsGamesStudio;
        } else {
            String booksStudio[] = {
                    "Hydra Books", "Hydra Books", "Hydra Books", "Hydra Books", "Hydra Books"
            };
            return booksStudio;
        }
    }

    private static String[] getCardBacgroundImagesByCategory(String category) {
        if (TextUtils.equals(category, HUB_CATEGORY[0])) {
            String quizBackgroundImages[] = {
                    "http://www.leadernetworks.com/wp-content/uploads/2017/04/quiz-background-slider.png",
                    "http://www.leadernetworks.com/wp-content/uploads/2017/04/quiz-background-slider.png",
                    "http://www.leadernetworks.com/wp-content/uploads/2017/04/quiz-background-slider.png",
                    "http://www.leadernetworks.com/wp-content/uploads/2017/04/quiz-background-slider.png",
                    "http://www.leadernetworks.com/wp-content/uploads/2017/04/quiz-background-slider.png",
            };
            return quizBackgroundImages;
        } else if (TextUtils.equals(category, HUB_CATEGORY[1])) {
            String boardBackgoundImages[] = {
                    "https://i.all3dp.com/wp-content/uploads/2017/08/27010431/pexels-photo-84663-2-e1502277883820.jpeg",
                    "https://i.all3dp.com/wp-content/uploads/2017/08/27010431/pexels-photo-84663-2-e1502277883820.jpeg",
                    "https://i.all3dp.com/wp-content/uploads/2017/08/27010431/pexels-photo-84663-2-e1502277883820.jpeg",
                    "https://i.all3dp.com/wp-content/uploads/2017/08/27010431/pexels-photo-84663-2-e1502277883820.jpeg",
                    "https://i.all3dp.com/wp-content/uploads/2017/08/27010431/pexels-photo-84663-2-e1502277883820.jpeg",
            };
            return boardBackgoundImages;
        } else if (TextUtils.equals(category, HUB_CATEGORY[2])) {
            String kidsImages[] = {
                    "http://holytrinity.herts.sch.uk/wp-content/uploads/2015/06/background1.jpg",
                    "http://holytrinity.herts.sch.uk/wp-content/uploads/2015/06/background1.jpg",
                    "http://holytrinity.herts.sch.uk/wp-content/uploads/2015/06/background1.jpg",
                    "http://holytrinity.herts.sch.uk/wp-content/uploads/2015/06/background1.jpg",
                    "http://holytrinity.herts.sch.uk/wp-content/uploads/2015/06/background1.jpg",
            };
            return kidsImages;
        } else {
            String bookImages[] = {
                    "https://i.imgur.com/TMQmpeN.jpg",
                    "https://i.imgur.com/TMQmpeN.jpg",
                    "https://i.imgur.com/TMQmpeN.jpg",
                    "https://i.imgur.com/TMQmpeN.jpg",
                    "https://i.imgur.com/TMQmpeN.jpg",
            };
            return bookImages;
        }
    }

    private static String[] getCardImagesByCategory(String category) {
        if (TextUtils.equals(category, HUB_CATEGORY[0])) {
            String quizImages[] = {
                    "https://i.imgur.com/RvQ96TO.jpg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_p5JUwvD80XJc2Z7v4Yq4dcMsOkUfylJXEujjyIQ4pDyC0oYU",
                    "https://i.ytimg.com/vi/GfdpZvzJr4I/maxresdefault.jpg",
                    "https://i.imgur.com/yTWgOdn.jpg",
                    "https://upload.wikimedia.org/wikipedia/sh/2/23/Milioner_logo.jpg"
            };
            return quizImages;
        } else if (TextUtils.equals(category, HUB_CATEGORY[1])) {
            String boardTitles[] = {
                    "http://www.gdvludica.it/wp-content/uploads/2015/02/coloni_catan01.jpg",
                    "https://www.pockettactics.com/wp-content/uploads/2014/01/mage_knight_1000.jpg",
                    "https://cdn.dnaindia.com/sites/default/files/styles/full/public/2016/02/21/428683-monopoly-wiki.jpg",
                    "http://www.theboardgamefamily.com/wp-content/uploads/2013/07/KBNomadsFull.jpg",
                    "https://is5-ssl.mzstatic.com/image/thumb/Purple128/v4/05/cc/d4/05ccd403-b308-95c2-8f54-e3171c838d38/source/800x500bb.jpg"
            };
            return boardTitles;
        } else if (TextUtils.equals(category, HUB_CATEGORY[2])) {
            String kidsTitles[] = {
                    "https://stmed.net/sites/default/files/cartoon-wallpapers-26240-4205350.jpg",
                    "http://www.dream-wallpaper.com/free-wallpaper/cartoon-wallpaper/children-games-3-wallpaper/1600x1200/free-wallpaper-10.jpg",
                    "http://demos.addictedtoweb.com/avcms/kidsoo/web/images/B/Basket_ball_kids.jpg",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQphYrpI7QbeNzyBijbyHcZXjogfsMNe9YI750VD2OHF3ZkgfmD",
                    "http://www.dream-wallpaper.com/free-wallpaper/cartoon-wallpaper/children-games-1-wallpaper/1920x1440/free-wallpaper-33.jpg",
            };
            return kidsTitles;
        } else if (TextUtils.equals(category, HUB_CATEGORY[2])) {
            String kidsTitles[] = {
                    "Catana",
                    "Puerto Rico",
                    "Monopoly",
                    "Dominion",
                    "Ticket To Ride"
            };
            return kidsTitles;
        } else {
            String bookImages[] = {
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-d0E9w6udjEnJ3oh532cxsHPUU80eqn8PMnAaKrS8Rc8ThYp1",
                    "https://img00.deviantart.net/6583/i/2015/124/f/3/twilight_book_cover_series_3rd_by_knucklestheechidna53-d21i2zf.jpg",
                    "https://davidkovalenko.files.wordpress.com/2013/12/catching-fire-book-covers.jpg",
                    "https://blackplume.files.wordpress.com/2011/09/samecoverart08.jpeg",
                    "http://andreapenrose.com/wp-content/uploads/Wrexford-Sloane-composite-covers-2-644x467.jpg",
            };
            return bookImages;
        }
    }

    private static HubItem buildHubItemInfo(
            String title,
            String description,
            String studio,
            String videoUrl,
            String cardImageUrl,
            String backgroundImageUrl,
            String category) {
        HubItem hubItem = new HubItem();
        hubItem.setId(count++);
        hubItem.setTitle(title);
        hubItem.setDescription(description);
        hubItem.setStudio(studio);
        hubItem.setCardImageUrl(cardImageUrl);
        hubItem.setBackgroundImageUrl(backgroundImageUrl);
        hubItem.setVideoUrl(videoUrl);
        hubItem.setCategory(category);
        return hubItem;
    }
}