# VenueHireSystem
## What I Did:
Designed and implemented a prototype system that could serve as the "back-end" of a venue hire system (such as for holding conferences).

In this venue hire system, customers can make, change and cancel reservations. Each venue has a number of rooms of various sizes: rooms are either small, medium or large. A reservation request has a named identifier and is for one or more rooms of a certain size (e.g. two small rooms and one large room) for a period of time given by a start date and an end date. A reservation request is either granted in full or is completely rejected by the system (a request cannot be partially fulfilled).

The implementation inputs and outputs data in the JSON format:

* Specify that venue *venue* has a room with name *room* of size *size*.

    > { "command": "room", "venue": *venue*, "room": *room*, "size": *size* }

* Request *id* is from *startdate* to *endate* for *small* number of small rooms, *medium* number of medium rooms, and *large* number of large rooms.

    > { "command": "request", "id": *id*, "start": *startdate*, "end": *enddate*, "small": *small*, "medium": *medium*, "large": *large* }

* Change existing reservation *id* to be from *startdate* to *endate* for *small* number of small rooms, *medium* number of medium rooms, and *large* number of large rooms.

    > { "command": "change", "id": *id*, "start": *startdate*, "end": *enddate*, "small": *small*, "medium": *medium*, "large": *large* }

* Cancel reservation *id* and free up rooms
    > { "command": "cancel", "id": *id* }

* List the occupancy of each room in the venue *venue*
    > { "command": "list", "venue": *venue* }

## How to run:
In VSCode, you can run the VenueHireSystem main function:
* Copy paste everything from input json file to the terminal once it is run

## What I learned:
This was my first time working with java on any project, and thus I was able to get a grasp of the many similarities between java and languages like C and C# but also their differences.
* Learned about classes and how they work
	* Learned about specifics of how classes interact with and work with other classes
* Learned how Java arrays work - Initialisation, difference between List and ArrayList
	* Learned about using arrays of Objects and Classes
* Learned about looping through ArrayList<Object>
* Learned more about java - still a new language for me
