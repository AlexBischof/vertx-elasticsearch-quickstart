curl -XPUT 'http://127.0.0.1:9200/myindex/blogpost/1' -d '
  {
    "title": "Nest eggs",
    "body":  "Making your money work...",
    "tags":  [ "cash", "shares" ],
    "comments": [
      {
        "name":    "John Smith",
        "comment": "Great article",
        "age":     28,
        "stars":   4,
        "date":    "2013-09-01"
      },
      {
        "name":    "Alice White",
        "comment": "More like this please",
        "age":     31,
        "stars":   15,
        "date":    "2013-10-22"
      }
    ]
  }'