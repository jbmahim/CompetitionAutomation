# CompetitionCoordinator
This repository includes a  comprehensive, user-friendly system designed for seamless management of competitions across various categories. It streamlines the entire competition process, from participant registration to score tracking and results analysis. 

Overview: Various people take part in a competition. You can choose the type of
competition -- it could be anything, from ice skating, tennis, football
(soccer), athletics or some electronic game.

A system is required to keep details of a competition, which has
various categories for competitors. Before the competition,
competitors register by filling in their details using an online form or
using an app on a mobile device. On the day of the competition,
competitors are awarded scores for their performance. These scores
are typed into the system by a member of staff, who first searches for
the competitor using their number.
When all the people in a given category have competed, a staff
member requests details of the results..
After the competition is over, competitors and staff can search for a
particular competitor using their number, and view their details,
including the basic and overall scores. They can also print out various
summary reports.
Here is a bit more detail about the registration process. Competitors
enter their name, email, date of birth, category and level, and are
supplied with a unique competitor number. If any fields are omitted,
an error message is displayed and the competitor is asked to resubmit
the form. If a competitor already exists with the same email address
and the same category, the registration is refused. If a competitor
already exists with the same email address and for a different
category, their registration is accepted and they are allocated a
different competitor number for this category. If a competitor's age is
incompatible with the level, the competitor is offered the opportunity
to resubmit the form for a different level. If everything is 0k, the
competitor is allocated a customer number and the registration is
accepted.
Again, each competitor gets a fixed number of scores, and a
calculation is done with these scores to determine their overall
score. The staff managing the competition require an intuitive
interactive GUI-based that has a web and mobile interface. Typically,
staff record competitor details, the competition they are taking part
in, and their scores. Moreover, not all staff have the same access level
to the system. Also, officials should be able to register competitors
on arrival, or remove competitors who have not showed up, or failed
to meet competition rules, or amend competitor details should
anything have changed since their initial registration. On-site fans
and other audience can watch proceedings as competitions progress,
and those not close enough can follow proceedings displayed on
screens strategically located around the competition venue. Besides
referees and data entry staff, there are emergency response services
present to provide support to the competitors, staff and audience.

In this assignment, you will design and create a realistic application to
hold details of competitions, competitors, staff, and produce a
report including details of all competitors, the winner, and some
summary statistics. The reports can be in the form of a chart
(visualization) or console output. Data persistence is important just in
case the sports administration team want to perform some
performance analysis after the event. You should not use any
proprietary database technology for data persistence - CSV or JSON
files should suffice.

Example of input data pertaining to competitors:
100, Keith John Talbot, Novice, 5,4,5,4,3
101, Jane Macdonald, Novice, 3,3,3,2,4
106, Karen Harding, Expert, 5,5,5,4,4
107, Sarah Green, Expert, 4,4,5,4,3
