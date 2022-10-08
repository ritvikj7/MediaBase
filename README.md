# Mediabase
## My Personal Project

The purpose of this application is to **store and rank different sorts of media a user has watched or read**—that could 
be movies, tv-shows, anime, novels, manga, webtoons. Possible features include:
- Multiple categories to differentiate types of media consumed
- An internal ranking system where a user can rank media specifically to the type it is 
- An external ranking system where they can rank all the media they have consumed regardless of type. 
- A synopsis or note sections that acts as a reminder to why they ranked a movie or anime where they initially did
- Fields to store important data such as director/author or year of release/publication or genre.

The idea behind this project is inspired by my passion for Manhwa. Manhwas are South Korean graphic novel which have 
become super popular in the past few years. As I started reading these comics, I found no software or app which could 
keep track of the comics I have read and rank them according to my preference. Therefore, I have simply used a Google 
doc to meet my needs. I further noticed that such apps or websites exist for other popular forms of media such as 
movies or anime. But the problem with such apps and websites are that they are focused solely on one form of media. For 
example, on MAL (my anime list) I can only store and rank the anime I have watched; similarly on IMDB I can only do 
that for movies. *So my need to have a place to store Manhwa, and my desire to rank different pieces of media regardless
of form inspired me to attempt this as my 210 project*. This project can be useful to many people because we all rely on
some form of media whether that be TV, books, music, or comics as a source of entertainment, and many of us want to
keep track of what we do, as we can see by the rise of applications such as notes. Merging these two things together, 
this project will focus on meeting the needs of users who wish to keep track of what they have consumed across 
different forms of media and who also love to rank visual arts, literary pieces, graphic novels and much more.


## User Stories
- As a user I want to be able to select a media form (such as movies, novels, anime) and add a single piece of media 
into it correctly (such as Inception, To Kill a Mockingbird, Haikyuu).
- As a user I would want to rate different pieces of media based on a scale of 10. 
- As a user I want to order the added pieces of media based on their ratings, highest to lowest, within a category and 
  retrieve their ranking. 
- As a user I want to create not only a media but also different media categories such as movies, novels, anime under 
  which the specific media will be categorized.
- As a user, I want to be able to save my media categories (list of categories) and the individual media within the 
  category to file. 
- As a user, I want to be able to load my media categories (list of categories) and the individual media within the 
  category from file.
- As a user, I want to see all the rankings from the best to worst of media in a category at a single place.



## Phase 4: Task 2
Thu Aug 11 02:25:43 PDT 2022
Created a new Media Category

Thu Aug 11 02:25:47 PDT 2022
Created a new Media Category

Thu Aug 11 02:26:04 PDT 2022
Added Media to a Media Category

Thu Aug 11 02:26:12 PDT 2022
Added Media to a Media Category

Thu Aug 11 02:26:32 PDT 2022
Removed Media from a Media Category


Process finished with exit code 0


## Phase 4: Task 3
- The very first thing I would do is to create an abstract class which all the classes responsible for the Pop-up 
messages will extend. All classes responsible for the Pop-up messages in the UI are very similar, except the message
printed and the size of the message is different for each pop-up message. Therefore, I will simply override the method 
popUpWindowMessages and tailor it to the desirable size it needs to be and what it needs to print based on the message.
This will reduce a lot of duplication as well.
- Another thing I would do is add exception handling. My code won’t break, but the way it is designed is that a media 
rating can only be from 0-10. But if a user types in an integer not within the legal range, an exception should be 
thrown to indicate that.  
- Furthermore, the way I designed the program is that if a media that already exists in a list of media is being added
again, rather than throwing an exception to indicate that media already exists, it simply informs the user that the 
media has been added even though it does not add it for the second time. Adding exception handling in such a case would 
also be nice. 
- Another big change I would make is the way I designed the layout of my MediaBaseGui class. Since I set the layout as 
null, that results in me having to manually set specific values for setBounds of different JPanels and JLogo displayed 
on the JFrame.  Now if I change the size of the JFrame, the JPanel will not align with one another and be all over the 
place. If I want to fix such a problem currently, I will have to go through every JPanel or JLogo I added and change 
their setBoundValue one at a time. In retrospect, and if I had more time I would have defined specific constants that 
will allow me to have a Single Point of Control—so if I do change the size of the JFrame, I will just need to make 
slight changes at one place.
- In MediaBaseGUI class I also need to increase cohesion. That class does way too many things: creates JFrame, creates
various JPanels, creates more JPanels, adds Buttons, Labels and TextFields to those the JPanels, and then does specific
tasks like add, remove, view, create media or media category. I should definitely have the MediaBaseGUI class design 
the JFrame, but delegate the responsibility of correctly adding, removing, viewing, and creating media or media 
category to a new class. I can increase even more cohesion by creating not only one but four different classes which 
deal with each of the responsibilities. The only problem I see is that passing parameters from the MediaBaseGUI class 
to another class may make the implementation a bit more complex, but I certainly think it is doable, and it will help
with increasing cohesion.


## note to user
Currently, nothing in the mediaBase has been saved. So when you do use it, you should first create a media category, 
like Movies or Manga or Anime—whatever you like. From there you can add medias to that media category you created. Be
careful that you type the exact name of the media category for "pick a media category", that means not only the spelling
have to be correct but also if the letters are uppercase or lowercase. You can add as many medias or remove as many
medias as you like. You can also view the medias you have added to a media category by adding the media category you 
want to view for "Select Media Category" and then by clicking view. You can save what you have created if you like. 

## what I want to do
- I want to be able to change the rating of the media
- I want to be able to have a drop-down menu for all possible media-category
- I want to be able to disable buttons if they at a certain point can't be used
- I want to be able to have pseudo text that tells a person what they should add
- I want to throw exception for invalid ratings
- I want to have exit browsers after a certain period of time
- I want to reduce duplication for the pop-up-messages.


