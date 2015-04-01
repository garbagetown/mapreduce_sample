require 'apache-loggen/base'
require 'digest/sha1'

class MyGen < LogGenerator::Apache
  def format(record, config)
    record['user'] = Digest::SHA1.hexdigest(grand(100).to_s)
    return %[#{record['host']} - #{record['user']} [#{Time.now.strftime('%d/%b/%Y:%H:%M:%S %z')}] "#{record['method']} #{record['path']} HTTP/1.1" #{record['code']} #{record['size']} "#{record['referer']}" "#{record['agent']}" #{record['process_time']}\n]
  end
end
LogGenerator.generate(nil, MyGen.new)
