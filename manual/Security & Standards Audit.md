# Security & Standards Audit

Some of these obviously don't really apply specifically to the subject under test but I included whatever popped up in my head.

## Security

1. Client ID and secret left base 64 encoded in `/script.js`. This isn't great as its providing authentication credentials to a potential attacker and.
2. XXS protection has not been considered meaning you can inject malicious code onto the page (tried the old `<script>document.write("");</script>` as one of the fields and it broke everything).
3. CORS has not been considered meaning that the endpoints can essentially be hit from anywhere by anyone.
4. HTTPS is not used meaning it's an easy target for MITM attacks.
5. Zero active validation on the data submitted, since everything is sent as JSON down the wire it's unlikely that SQL injection (if it's even using a SQL database) is a valid attack vector (we know XSS is) but relying on type casting (assumption) does not a validation strategy make.
6. No authentication mechanism (however redundant) for `GET`ing and `POST`ing only `DELETE`ing.. abit strange.

## Standards

1. HTTP response codes used improperly:
	* 201 for example when a resource is removed (should be 200, 202 or 204). 200 is also used instead of 201 for a resource creation.
	* 500 instead of 400 for attempting to submit bad or incomplete data (suspect thats from exceptions thrown when trying to marshall into objects)
	* 400 is used when you attempt to send JSON with an XML content type which  isn't bad but a better status code would be 415.
	* 404 instead of 405 for methods that aren't supported (PATCH, PUT etc).
	* 403 when authentication details aren't provided instead of 401.
2. Basic Accessibility... problems only with what's present.
	* Form elements don't have labels (or place holders for that matter). Means that voice over applications don't know what the inputs are for an state simply "blank", `aria-` tags would be even better.
	* `lang` attribute missing from the HTML declaration, helps screen readers choose a language profile (also not valid HTML without it).

## Observations

1. More an observation but the choosen responsive approach (or lack of one) doesn't lend itself well to the content from a usability, experience or accessibility point of view.
2. Creating a booking does not return a canonical representation of a booking object, it probably should.