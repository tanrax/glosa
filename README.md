<p align="center">
  <img src="logo.png" alt="logo" width="200"> 
</p>
<h1 align="center">Glosa: Comments for static sites.</h1>
<p align="center">
   <strong >Clone of Disqus, but faster, Opensource and sexy.</strong>
</p>

## Amazing reasons to use it

- Only 2ms to respond.
- No database.
- Configuration in a simple YAML.
- Easy to import from Disqus.
- Captcha system included.
- Easy to integrate with static pages.
- Opensource.

## Usage

Download Glosa from folder.

``` sh
dist/glosa-[last version]-standalone.jar
```

Run.

```sh
java -jar glosa-[last version]-standalone.jar
```

Great 🎉. You already have your 🔥 own comment server 🔥.

## Install

``` sh
cp config.yaml.example config.yaml
```

Edit `config.yaml`.

``` yaml
domain: example.com
port: 4000
debug: true
subject: New comment
from: server@example.com
to: user@example.com
smtp-host: smtp.example.com
smtp-user: smtpuser
smtp-password: smtppassword
smtp-port: 25
smtp-tls: true
```

Run.

``` sh
java -jar glosa-[last version]-standalone.jar
```

That's it, now you just have to test that it works properly.

``` sh
curl localhost:4000/api/v1/captcha/
```

It will return a random token

``` json
{"token":"OABWNONEOOKXRMMWADPF"}
```

## Testing

``` sh
lein check-idiomatic
lein check-format
```

## API

### Get Comments

GET: Gets all the comments on one page.

``` sh
/api/v1/comments/?url={url}
```

| Param | Value  | Description |
|---|---|---|
| url  | string | Page where you want to get the comments. |

#### Example

Get from `https://glosa.example/best-SO/`.

``` sh
curl 'https://programadorwebvalencia.localhost:4000/api/v1/comments/?url=https://glosa.example/best-SO/'
```

#### Success response

``` json
[
    {
        "id": 4812781236,
        "parent": "",
        "createdAt": 1584266634,
        "thread": "https://glosa.example/best-SO/",
        "author": "Lexar",
        "message": "Do you use Glosa too? It's an amazing technology."
    },
    {
        "id": 4812781237,
        "parent": "",
        "createdAt": 1584266746,
        "thread": "https://glosa.example/best-SO/",
        "author": "Lucia",
        "message": "I love the article."
    }
]
```

#### Fail response

``` json
[]
```

### Add Comment

POST: Add new comment on one page. After saving the comment the token will no longer be valid.

``` sh
/api/v1/comments/?url={url}
```

| Param | Value | Description |
|---|---|---|
| parent  | number | If it's a sub-comment, the number of the parent comment. Otherwise leave empty. |
| author  | string | Author's name. |
| message  | string | Message. It can be HTML or plain. |
| token  | number | Number of the token generated by the captcha endpoint. |
| thread  | string | Page where you want to save the comment. |

#### Example

Save comment from `https://glosa.example/best-SO/`.

``` sh
curl -H "Content-type: application/json" -d '{
	"parent": "",
	"token": "VRJUOBBMTKFQUAFZOKJG",
	"author": "Juana",
	"message": "I like it very much.",
	"thread":"https://glosa.example/best-SO/"
}' 'https://glosa.example:4000/api/v1/comments/'
```

#### Success response

``` json
{
    "status": 200
}
```

#### Fail response


``` json
{
    "status": 401
}
```

### Get captcha token

GET: Get a token to validate that a new comment can be created. It has only one use. It must also be obtained 20 seconds before use or it will not work.

``` sh
/api/v1/captcha/?url={url}
```

| Param | Value  | Description |
|---|---|---|
| url  | string | Page where you want to save the comment. |

#### Example

Get token for page `https://glosa.example/best-SO/`.

``` sh
curl 'https://glosa.example:4000/api/v1/captcha/?url=https://glosa.example/best-SO/'
```

#### Success response

``` json
{
    "token": "ZRFOKXLALKNPOJPYJLVY"
}
```

#### Fail response


``` json
{
    "token": ""
}
```

---

## Import from Disqus
https://github.com/tanrax/glosa-disqus-import

---

## Connect with your site (HTML and Javascript)
https://github.com/tanrax/glosa-cli

---

<p align="center">
  Thanks to the power of <a href="https://tadam-framework.dev/"><img src="https://avatars3.githubusercontent.com/u/54397807?s=50&v=4" alt="logo" width="50"> Tadam Framework</a>
</p>
