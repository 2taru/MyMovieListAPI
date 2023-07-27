
# My Movie List

This is a program in which users can view information about movies and add those they have watched to their list of watched movies. You can also rate and write a description (for example, what did you like about the movie you watched)


## Features

- User registration and login
- Ability to add movies to the "Watched List", rate them and give a description
- Ability to sort movies
- Endpoints with restricted roles
- Exception handler
- Unit tests with Mockito


## Technologies, Frameworks and other
- Spring Boot 3
- Spring Data JPA
- Spring Security 6
- MySQL Database
- JUnit 5
## API Endpoints

#### Get all movies

```http
  GET /api/movie?pageNo=0&pageSize=10&sortBy=imdbRating&sortType=DESC
```

| Parameter   | Type          | Description                     |
| :---------- | :------------ | :------------------------------ |
| `pageSize`  | `int`         | **Not Required**. Default = 10  |
| `pageNo`    | `int`         | **Not Required**. Default = 0   |
| `sortBy`    | `String`      | **Not Required**. Default = imdbRating. Available options : imdbRating, seriesTitle, genre, runtime, releasedYear. |
| `sortType`  | `String`      | **Not Required**. Default = DESC. Available options : DESC, ASC. |

#### Get movie by id

```http
  GET /api/movie/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. Id of movie         |

#### Create User

```http
  POST /api/user/create
```

JSON body example

```http
{
    "username": "create",
    "email": "create@mail.com",
    "password": "create"
}
```

#### Update User

```http
  PUT /api/user/${id}/update
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. Id of user          |

JSON body example

```http
{
    "username": "upadate",
    "email": "upadate@mail.com",
    "password": "upadate"
}
```

#### Get user by id

```http
  GET /api/user/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. Id of user          |

#### Delete user by id

```http
  DELETE /api/user/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. Id of user          | 


#### Create MovieListItem

```http
  POST /api/movieListItem/create?userId=7&movieId=98
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `userId`  | `int`    | **Required**. Id of user          |
| `movieId` | `int`    | **Required**. Id of movie         |

#### Update MovieListItem

```http
  PUT /api/movieListItem/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. Id of user          |

JSON body example

```http
{
    "status": "Completed",
    "score": 9,
    "description": "Great",
}
```

#### Get MovieListItem by id

```http
  GET /api/movieListItem/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `int`    | **Required**. Id of MovieListItem |

#### Get MovieListItems by userId

```http
  GET /api/movieListItem?userId=7&pageNo=0&pageSize=10&sortBy=score&sortType=DESC
```

| Parameter  | Type          | Description                  |
| :--------- | :------- | :-------------------------------- |
| `userId`   | `int`    | **Required**. Id of user.         |
| `pageSize` | `int`    | **Not Required**. Default = 10    |
| `pageNo`   | `int`    | **Not Required**. Default = 0     |
| `sortBy`   | `String` | **Not Required**. Default = score. Available options : score, status. |
| `sortType` | `String` | **Not Required**. Default = DESC. Available options : DESC, ASC. |

#### Delete MovieListItem by id

```http
  DELETE /api/movieListItem/${id}
```

| Parameter | Type     | Description                        |
| :-------- | :------- | :--------------------------------- |
| `id`      | `int`    | **Required**. Id of MovieListItems |

## Authors

- [@2taru](https://www.github.com/2taru)

