curl -XPOST 'http://127.0.0.1:9200/myindex/blogpost' -d '
{
  "title": "Nest eggs2",
  "body":  "Making your money work...",
  "tags":  [ "cash", "shares" ],
  "comments": [
    {
      "name":    "John Smith",
      "comment": "Great article",
      "age":     28,
      "stars":   4,
      "date":    "2014-09-01"
    },
    {
      "name":    "Alice White",
      "comment": "More like this please",
      "age":     31,
      "stars":   15,
      "date":    "2014-10-22"
    }
  ]
}'