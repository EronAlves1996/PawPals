<center>
<h1>PawPals API</h1>
<img src="./pawpalsLogo.jpg" alt="Pawpals logo">
<hr>
</center>

Pawpals is an API centered in pet adoption, by matching users preferences to pet characteristics.

- Register yourself;
- Put your preferences;
- Start finding matches;
- Adopt a pet.

If you know or have some pet that you want to give to adoption, you can register the pet. Soon, someone gonna adopt and give home to him!

## Stack

The API was entire generated with Spring Boot, using the modules bellow:

- Spring Web MVC
- Spring Data JPA
- Hibernate Validators

## Running the project

You can get some demonstration on your machine by running the entire project on docker. You should have:

- Docker
- Docker compose

Just run:

```sh
docker compose up
```

It gonna takes 5 minutes to get up and running. After that the application will listen in port 8080.

## Using the api

After running the project, the endpoint decriptions are available on http://localhost:8080/pawpals/swagger-ui.html.

Basically, the flow are following:

- Register as a user running a POST call into http://localhost:8080/pawpals/users/register (id information is not required, its autogenerated);
- Register your pet preferences by running a POST call into http://localhost:8080/pawpals/users/{id}/adoption (fill the id information with your user id);
- Match your preferences against the pet availables running a GET call into http://localhost:8080/pawpals/users/matches/{id};
- Choose an animal and adopt him, by running a DELETE call into http://localhost:8080/pawpals/animals/{id} (fill the id information with the id of adopted animal);
- If you don't want to adopt anymore, make yourself unavailable by running a PUT call into http://localhost:8080/pawpals/users/no-adopt/{id} (fill the id information with your user id).

If you know or want to give some pet to adoption, run a POST call into http://localhost:8080/pawpals/animals/register.

You can test the application right now in the